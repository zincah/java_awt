package zinc.java.pro01;

import java.awt.*;
import java.awt.event.*;


public class TodoList extends Frame 
		implements ActionListener, FocusListener, WindowListener{

	private static final long serialVersionUID = 1L;
	
	// 화면 구현 요소
	// date
	private Choice month = new Choice();
		private String[] mstr = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
	private Choice day = new Choice();
	private TextField date = new TextField(30);
	private TextField sub = new TextField(30);
	
	// todo list
	private TextField to1 = new TextField(20);
	private TextField to2 = new TextField(20);
	private TextField to3 = new TextField(20);
	private TextField to4 = new TextField(20);
	private TextField to5 = new TextField(20);
	
	// plan
	private TextField pl1 = new TextField(25);
	private TextField pl2 = new TextField(25);
	private TextField pl3 = new TextField(25);
	private TextField pl4 = new TextField(25);
	private TextField pl5 = new TextField(25);
	
	// memo
	private TextArea memo = new TextArea();
	
	// button
	private Button bt1 = new Button("setting");
	private Button bt2 = new Button("Pause");
	private Button bt3 = new Button("reset");
	
	
	// 메뉴 바
	private MenuBar bar = new MenuBar();
	private Menu file = new Menu("File");
		private MenuItem fnew = new MenuItem("new file");
		private MenuItem fopen = new MenuItem("open file");
		private MenuItem fsave = new MenuItem("save file");
		private MenuItem fsaveas = new MenuItem("save as file");
		private MenuItem fexit = new MenuItem("exit");
	private Menu help = new Menu("Help");
		private MenuItem impo = new MenuItem("version information");
	
	// 파일
	FileDialog fdopen = new FileDialog(this, "open file..", FileDialog.LOAD);
	FileDialog fdsave = new FileDialog(this, "save file..", FileDialog.SAVE);
	
	// 모달
	private Dialog d = new Dialog(this, "Version Information", false);
	private Button check = new Button("check");
	
	// 생성자
	public TodoList() {
		super("new plan");
		setMenu();
		setBody();
		setEvent();
		setInpoDialog();
	}
	
	public static void main(String[] args) {
		new TodoList();
	}
	
	public void setMenu() {
		setMenuBar(bar);
		bar.add(file);
			file.add(fnew);
			file.addSeparator();
			file.add(fopen);
			file.add(fsave);
			file.add(fsaveas);
			file.addSeparator();
			file.add(fexit);
		bar.add(help);
			help.add(impo);
	}
	
	public void setBody() {
		Color back = new Color(0x756E74);
		setBackground(back);
		setLayout(new BorderLayout());
		add("North", new Label());
		add("South", new Label());
		add("West", new Label());
		add("East", new Label());
		
		Panel mainPanel = new Panel(new BorderLayout(3,3));
		Panel titlePanel = new Panel(new BorderLayout(5,5));
			Label name = new Label("My Plan...");
			name.setFont(new Font("consolas", Font.BOLD, 20));
			titlePanel.add("North", name);
			
			Panel titleName = new Panel(new GridLayout(2,1,5,5));
			titleName.add(new Label("Date : ", Label.LEFT));
			titleName.add(new Label("Subject : ", Label.LEFT));
			titlePanel.add("West", titleName);
			
			Panel titleBlank = new Panel(new GridLayout(2,1,5,5));
			Panel blank1 = new Panel(new FlowLayout(FlowLayout.LEFT));
			blank1.add(date);
			titleBlank.add(blank1);
			Panel blank2 = new Panel(new FlowLayout(FlowLayout.LEFT));
			blank2.add(sub);
			titleBlank.add(blank2);
			titlePanel.add("Center", titleBlank);
			
			Panel choDate = new Panel(new GridLayout(3,1,3,3));
			choDate.add(new Label("Set Date", Label.CENTER));
			for(String mon : mstr) month.add(mon + "월");
			choDate.add(month);
			for(int i=1; i<=31; i++) {
				day.add(String.valueOf(i) + "일");
			}
			choDate.add(day);
			titlePanel.add("East", choDate);
		mainPanel.add("North", titlePanel);
		add("Center", mainPanel);
		
		Panel centerPanel = new Panel(new BorderLayout());
		centerPanel.add("North", 
				new Label("-------------------------------------", Label.CENTER));
			Panel todoList = new Panel(new GridLayout(6,1,3,3));
			todoList.add(new Label("To Do List"));
			Panel todo1 = new Panel(new FlowLayout(FlowLayout.LEFT));
			todo1.add(new Checkbox(""));
			todo1.add(to1);
			todoList.add(todo1);
			Panel todo2 = new Panel(new FlowLayout(FlowLayout.LEFT));
			todo2.add(new Checkbox(""));
			todo2.add(to2);
			todoList.add(todo2);
			Panel todo3 = new Panel(new FlowLayout(FlowLayout.LEFT));
			todo3.add(new Checkbox(""));
			todo3.add(to3);
			todoList.add(todo3);
			Panel todo4 = new Panel(new FlowLayout(FlowLayout.LEFT));
			todo4.add(new Checkbox(""));
			todo4.add(to4);
			todoList.add(todo4);
			Panel todo5 = new Panel(new FlowLayout(FlowLayout.LEFT));
			todo5.add(new Checkbox(""));
			todo5.add(to5);
			todoList.add(todo5);
		centerPanel.add("Center", todoList);
		mainPanel.add(centerPanel);
		
			Panel planList = new Panel(new GridLayout(6,1,3,3));
			planList.add(new Label("Planing.."));
			planList.add(pl1);
			planList.add(pl2);
			planList.add(pl3);
			planList.add(pl4);
			planList.add(pl5);
		centerPanel.add("East", planList);
		mainPanel.add(centerPanel);

		
		Panel memoPanel = new Panel(new BorderLayout(3,3));
		memoPanel.add("North", new Label("My Memo :)", Label.CENTER));
		memoPanel.add("Center", memo);
			Panel button = new Panel(new GridLayout(1, 3, 5, 5));
			button.add(bt1);
			button.add(bt2);
			button.add(bt3);
		memoPanel.add("South", button);
		mainPanel.add("South", memoPanel);
		
		setSize(500, 600);
		setResizable(false);
		
		// 화면 중앙 메소드
		Dimension sc = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension my = getSize();
		setLocation(sc.width/2 - my.width/2, sc.height/2 - my.height/2);
		
		setVisible(true);
	}
	
	// 버전 정보 다이얼로그
	public void setInpoDialog() {
		d.setLayout(new BorderLayout(3,3));
		d.add("North", new Label("Version Information", Label.CENTER));
		d.add("Center", new Label("Todo List.v.1.0 by zincah", Label.CENTER));
		d.setSize(260, 130);
		
		Dimension dialog = d.getSize();
		int x = getX() + (getWidth()/2 - dialog.width/2);
		int y = getY() + (getHeight()/2 - dialog.height/2);
		d.setBounds(x, y, 260, 130);
		d.setResizable(false);
		//d.setVisible(true);
	}
	
	public void setEvent() {
		
	}

	@Override
	public void windowOpened(WindowEvent e) {}

	@Override
	public void windowClosing(WindowEvent e) {}

	@Override
	public void windowClosed(WindowEvent e) {}

	@Override
	public void windowIconified(WindowEvent e) {}

	@Override
	public void windowDeiconified(WindowEvent e) {}

	@Override
	public void windowActivated(WindowEvent e) {}

	@Override
	public void windowDeactivated(WindowEvent e) {}

	@Override
	public void focusGained(FocusEvent e) {}

	@Override
	public void focusLost(FocusEvent e) {}

	@Override
	public void actionPerformed(ActionEvent e) {}
	
	
}

