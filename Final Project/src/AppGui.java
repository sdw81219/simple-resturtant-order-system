import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class AppGui extends JFrame{
    private JPanel topPanel;
    private JPanel centerPanel;
    private JPanel botPanel;
    JList list;
    private JLabel label;
    private JTextField field;
    private JButton button;
    private DefaultListModel model;
    private JScrollPane scrollPane;
    private JScrollPane scrollPane1;
    private JTextArea chatField;
    private  JTextField sendText;
    private String tempUserName;
    JLabel label2;
    Boolean loggedIn = false;
    Socket socket;
    DataOutputStream toServer = null;
    DataInputStream fromServer = null;
    JComboBox firstAmount;
    JComboBox secondAmount;
    JComboBox thirdAmount;
    JComboBox forthAmount;
    JComboBox fifthAmount;
    JComboBox sixAmount;
    JComboBox sevenAmount;
    JComboBox eightAmount;
    JComboBox nineAmount;
    JComboBox tenAmount;
    
    





    public AppGui()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setTitle("Order System");
        this.setSize(800,1000);

//        JPanel panelForTextFields = new JPanel();
//        panelForTextFields.setSize(400, 850);


        //panel at the top
        topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        centerPanel = new JPanel();
        GridLayout layout = new GridLayout(1,2,15,10);
        botPanel = new JPanel(layout);

        createMenus();
        createLogin();


        // There should probably be something passed into the JScrollPane

        model = new DefaultListModel();
        list = new JList(model);


//        panelForTextFields.add(scrollPane);
        add(topPanel,BorderLayout.NORTH);
        add(centerPanel,BorderLayout.CENTER);
        add(botPanel,BorderLayout.SOUTH);
        OderMenus();
        createChatField();
        OrderCheck();
        textSend();


        setVisible(true);



    }

    private void createLogin() {

        JLabel userName = new JLabel("username:");
        JLabel passWord = new JLabel("password:");

        JTextField userName1= new JTextField("Type your username");
//        JPasswordField passWord1 = new JPasswordField("Type your password")
        JTextField passWord1= new JTextField("Type your password");
        JButton login = new JButton("Login");
        JButton register = new JButton("Register");
        topPanel.add(userName,LEFT_ALIGNMENT);
        topPanel.add(userName1,LEFT_ALIGNMENT);
        topPanel.add(passWord,LEFT_ALIGNMENT);
        topPanel.add(passWord1,LEFT_ALIGNMENT);
        topPanel.add(login,LEFT_ALIGNMENT);
        topPanel.add(register,LEFT_ALIGNMENT);
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
            	DBdemo DB = new DBdemo();
            	Boolean result = DB.checkUser(userName1.getText(), passWord1.getText());
            	tempUserName = userName1.getText();
            	System.out.println(result);
                if(result && !loggedIn){
                    JOptionPane.showMessageDialog(null, "Successfully login",
                            null, JOptionPane.PLAIN_MESSAGE);
                    loggedIn = true;
                    label2.setText("Welcome: " + userName1.getText());
                    label2.setVisible(true);
                }
                else{
                	if (loggedIn) {
                		JOptionPane.showMessageDialog(null, "Aleady Logged In",
                                null, JOptionPane.PLAIN_MESSAGE);
                	}
                	else {
                		JOptionPane.showMessageDialog(null, "No such an account, please register a new account",
                                null, JOptionPane.PLAIN_MESSAGE);
                	}
                    
                }
            }
            
            
            
    });
        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
            	DBdemo DB = new DBdemo();
            	Boolean result = DB.checkUser(userName1.getText(), passWord1.getText());
            	System.out.println(result);
                if(!result && !loggedIn){
                	DB.register(userName1.getText(), passWord1.getText());
                    JOptionPane.showMessageDialog(null, "Successfully registered",
                            null, JOptionPane.PLAIN_MESSAGE);
                    loggedIn = true;
                    label2.setText("Welcome: " + userName1.getText());
                }
                else{
                	if (loggedIn) {
                		JOptionPane.showMessageDialog(null, "Aleady Logged In",
                                null, JOptionPane.PLAIN_MESSAGE);
                	}
                	else {
                		JOptionPane.showMessageDialog(null, "You have registered already, please Log in",
                                null, JOptionPane.PLAIN_MESSAGE);
                	}
                    
                }
            }
            
            
            
    });
        
        
        
        
    }
    private void OderMenus(){
        scrollPane = new JScrollPane();
        scrollPane.setPreferredSize(new Dimension(400,650));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        JPanel menusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        menusPanel.setLayout(new BoxLayout(menusPanel, BoxLayout.Y_AXIS));

        JPanel firstPanel = new JPanel();
        firstPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel firstDish = new JLabel("Kung Pao Chicken     $30");
//        JLabel firstDishPic = new JLabel();
        ImageIcon gongbao =new ImageIcon ("img//1.jpg");
        gongbao.setImage(gongbao.getImage().getScaledInstance(150,100,Image.SCALE_DEFAULT));
        //若是单独把图片放到一个label中，则图片会显示在右边
//        firstDishPic.setIcon(gongbao);
        firstDish.setIcon(gongbao);
        firstAmount =new JComboBox();
        firstAmount.addItem(0);
        firstAmount.addItem(1);
        firstAmount.addItem(2);
        firstAmount.addItem(3);
        firstAmount.addItem(4);
        firstAmount.addItem(5);
        firstPanel.add(firstDish);
        firstPanel.add(firstAmount);
        //        firstPanel.add(firstDishPic);

        JPanel secondPanel = new JPanel();
        JLabel secondDish = new JLabel("Dandan Noodles     $15");
        secondPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        ImageIcon banMian =new ImageIcon ("img//2.jpg");
        banMian.setImage(banMian.getImage().getScaledInstance(150,100,Image.SCALE_DEFAULT));
        secondDish.setIcon(banMian);
        secondAmount =new JComboBox();
        secondAmount.addItem(0);
        secondAmount.addItem(1);
        secondAmount.addItem(2);
        secondAmount.addItem(3);
        secondAmount.addItem(4);
        secondAmount.addItem(5);
        secondPanel.add(secondDish);
        secondPanel.add(secondAmount);

        JPanel thirdPanel = new JPanel();
        JLabel thirdDish = new JLabel("Nigiri     $45");
        thirdPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        ImageIcon haiDai =new ImageIcon ("img//10.jpg");
        haiDai.setImage(haiDai.getImage().getScaledInstance(150,100,Image.SCALE_DEFAULT));
        thirdDish.setIcon(haiDai);
        thirdAmount =new JComboBox();
        thirdAmount.addItem(0);
        thirdAmount.addItem(1);
        thirdAmount.addItem(2);
        thirdAmount.addItem(3);
        thirdAmount.addItem(4);
        thirdAmount.addItem(5);
        thirdPanel.add(thirdDish);
        thirdPanel.add(thirdAmount);

        JPanel forthPanel = new JPanel();
        JLabel forthDish = new JLabel("Sarma     $30");
        forthPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        ImageIcon BanliCake =new ImageIcon ("img//9.jpg");
        BanliCake.setImage(BanliCake.getImage().getScaledInstance(150,100,Image.SCALE_DEFAULT));
        forthDish.setIcon(BanliCake);
        forthAmount =new JComboBox();
        forthAmount.addItem(0);
        forthAmount.addItem(1);
        forthAmount.addItem(2);
        forthAmount.addItem(3);
        forthAmount.addItem(4);
        forthAmount.addItem(5);
        forthPanel.add(forthDish);
        forthPanel.add(forthAmount);


        JPanel fifthPanel = new JPanel();
        JLabel fifthDish = new JLabel("Pizza Margherita     $50");
        fifthPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        ImageIcon IceCream =new ImageIcon ("img//8.jpg");
        IceCream.setImage(IceCream.getImage().getScaledInstance(150,100,Image.SCALE_DEFAULT));
        fifthDish.setIcon(IceCream);
        fifthAmount =new JComboBox();
        fifthAmount.addItem(0);
        fifthAmount.addItem(1);
        fifthAmount.addItem(2);
        fifthAmount.addItem(3);
        fifthAmount.addItem(4);
        fifthAmount.addItem(5);
        fifthPanel.add(fifthDish);
        fifthPanel.add(fifthAmount);

        JPanel sixPanel = new JPanel();
        JLabel sixDish = new JLabel("Satay     $20");
        sixPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        ImageIcon appetizer =new ImageIcon ("img//7.jpg");
        appetizer.setImage(appetizer.getImage().getScaledInstance(150,100,Image.SCALE_DEFAULT));
        sixDish.setIcon(appetizer);
        sixAmount =new JComboBox();
        sixAmount.addItem(0);
        sixAmount.addItem(1);
        sixAmount.addItem(2);
        sixAmount.addItem(3);
        sixAmount.addItem(4);
        sixAmount.addItem(5);
        sixPanel.add(sixDish);
        sixPanel.add(sixAmount);



        JPanel sevenPanel = new JPanel();
        JLabel sevenDish = new JLabel("Banchan     $40");
        sevenPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        ImageIcon ChineseBBQ =new ImageIcon ("img//6.jpg");
        ChineseBBQ.setImage(ChineseBBQ.getImage().getScaledInstance(150,100,Image.SCALE_DEFAULT));
        sevenDish.setIcon(ChineseBBQ);
        sevenAmount =new JComboBox();
        sevenAmount.addItem(0);
        sevenAmount.addItem(1);
        sevenAmount.addItem(2);
        sevenAmount.addItem(3);
        sevenAmount.addItem(4);
        sevenAmount.addItem(5);
        sevenPanel.add(sevenDish);
        sevenPanel.add(sevenAmount);

        JPanel eightPanel = new JPanel();
        JLabel eightDish = new JLabel("Frozen Yogurt    $15");
        eightPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        ImageIcon Pizza =new ImageIcon ("img//5.jpg");
        Pizza.setImage(Pizza.getImage().getScaledInstance(150,100,Image.SCALE_DEFAULT));
        eightDish.setIcon(Pizza);
        eightAmount =new JComboBox();
        eightAmount.addItem(0);
        eightAmount.addItem(1);
        eightAmount.addItem(2);
        eightAmount.addItem(3);
        eightAmount.addItem(4);
        eightAmount.addItem(5);
        eightPanel.add(eightDish);
        eightPanel.add(eightAmount);


        JPanel ninePanel = new JPanel();
        JLabel nineDish = new JLabel("Nougat    $25");
        ninePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        ImageIcon Spaghetti =new ImageIcon ("img//4.jpg");
        Spaghetti.setImage(Spaghetti.getImage().getScaledInstance(150,100,Image.SCALE_DEFAULT));
        nineDish.setIcon(Spaghetti);
        nineAmount =new JComboBox();
        nineAmount.addItem(0);
        nineAmount.addItem(1);
        nineAmount.addItem(2);
        nineAmount.addItem(3);
        nineAmount.addItem(4);
        nineAmount.addItem(5);
        ninePanel.add(nineDish);
        ninePanel.add(nineAmount);

        JPanel tenPanel = new JPanel();
        JLabel tenDish = new JLabel("Kombu    $10");
        tenPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        ImageIcon sushi =new ImageIcon ("img//3.jpg");
        sushi.setImage(sushi.getImage().getScaledInstance(150,100,Image.SCALE_DEFAULT));
        tenDish.setIcon(sushi);
        tenAmount =new JComboBox();
        tenAmount.addItem(0);
        tenAmount.addItem(1);
        tenAmount.addItem(2);
        tenAmount.addItem(3);
        tenAmount.addItem(4);
        tenAmount.addItem(5);
        tenPanel.add(tenDish);
        tenPanel.add(tenAmount);





        menusPanel.add(firstPanel,LEFT_ALIGNMENT);
        menusPanel.add(secondPanel,LEFT_ALIGNMENT);
        menusPanel.add(thirdPanel,LEFT_ALIGNMENT);
        menusPanel.add(forthPanel,LEFT_ALIGNMENT);
        menusPanel.add(fifthPanel,LEFT_ALIGNMENT);
        menusPanel.add(sixPanel,LEFT_ALIGNMENT);
        menusPanel.add(sevenPanel,LEFT_ALIGNMENT);
        menusPanel.add(eightPanel,LEFT_ALIGNMENT);
        menusPanel.add(ninePanel,LEFT_ALIGNMENT);
        menusPanel.add(tenPanel,LEFT_ALIGNMENT);



        scrollPane.setViewportView(menusPanel);
        centerPanel.add(scrollPane);


        //oder and check its availability from the database and also calculate the total

    }


    private void createChatField(){
        chatField = new JTextArea();
        chatField.setCaretPosition(0);
        chatField.setEditable(false);
        JPanel chatPanel = new JPanel();
        chatPanel.setLayout(new BoxLayout(chatPanel, BoxLayout.Y_AXIS));
        scrollPane1 = new JScrollPane(chatField);
        scrollPane1.setPreferredSize(new Dimension(350,580));
        chatPanel.add(scrollPane1);
        sendText = new JTextField();
        sendText.setPreferredSize(new Dimension(350,70));
        ChatClient chating = new ChatClient();
        chatPanel.add(chating.textField());
        centerPanel.add(chatPanel);

    }

    private void createMenus() {
        /* add a "Option" menu with:
         * "Connect" costumer can connect the server
         * "Exit" item which ends the process with System.exit(0);
         * Key shortcuts are optional
         */
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        menuBar.add(createOptionMenu());
        label2 = new JLabel("Welcome:");
        setJMenuBar(menuBar);
        menuBar.add(Box.createHorizontalGlue());
        menuBar.add(label2);

    }
    private void OrderCheck(){
        JPanel orderPanel = new JPanel();
        JButton OrderButton = new JButton("Order");
        OrderButton.setPreferredSize(new Dimension(200,50));
        orderPanel.add(OrderButton);
        OrderButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
            	if(!loggedIn) {
            		JOptionPane.showMessageDialog(null, "You have not Logged in",
                            "Please Log in First", JOptionPane.PLAIN_MESSAGE);
            		return;
            	}
            	DBdemo DB = new DBdemo();
            	ArrayList<Integer> amount = new ArrayList<Integer>();
            	amount.add((Integer) firstAmount.getSelectedItem());
            	amount.add((Integer) secondAmount.getSelectedItem());
            	amount.add((Integer) thirdAmount.getSelectedItem());
            	amount.add((Integer) forthAmount.getSelectedItem());
            	amount.add((Integer) fifthAmount.getSelectedItem());
            	amount.add((Integer) sixAmount.getSelectedItem());
            	amount.add((Integer) sevenAmount.getSelectedItem());
            	amount.add((Integer) eightAmount.getSelectedItem());
            	amount.add((Integer) nineAmount.getSelectedItem());
            	amount.add((Integer) tenAmount.getSelectedItem());
            	System.out.println(amount);
            	Object cost = DB.Order(amount);
            	
            	if(!(cost instanceof Integer)){
            		JOptionPane.showMessageDialog(null, "Dish '" + cost + "' is sold out",
                            "Please reorder", JOptionPane.PLAIN_MESSAGE);
            	}else {
            		System.out.println(cost);
                    JOptionPane.showMessageDialog(null, "Thanks for your order!! your total is " + cost,
                            "ENJOY YOUR FOOD", JOptionPane.PLAIN_MESSAGE);
            	}
            	
            }
        });

        botPanel.add(orderPanel);


    }

    private void textSend(){
        JPanel textPanel = new JPanel();
        JButton textCheck = new JButton("send");
        textCheck.setPreferredSize(new Dimension(200,50));
        class ButtonListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    toServer = new DataOutputStream(socket.getOutputStream());
                }
                catch (IOException ex) {
                    chatField.append(ex.toString() + '\n');
                }

                try {
                    String sentMessage = sendText.getText();
//                    chatField.append("id:"+sentMessage+"\n");
                    toServer.writeUTF(sentMessage);
                    toServer.flush();
                }
                catch (IOException ex) {
                    System.err.println(ex);
                }
                sendText.setText("");
            }
        }
        textCheck.addActionListener(new ButtonListener());
        textPanel.add(textCheck);
        botPanel.add(textPanel,LEFT_ALIGNMENT);


    }


    private JMenu createOptionMenu() {
        JMenu menu = new JMenu("Option");
        menu.add(createConnectItem());
        menu.add(createExitItem());
        return menu;
    }

    private JMenuItem createExitItem() {
        JMenuItem item = new JMenuItem("Exit");
        class MenuItemListener implements ActionListener
        {
            public void actionPerformed(ActionEvent event)
            {
                System.exit(0);
            }
        }
        ActionListener listener = new MenuItemListener();
        item.addActionListener(listener);
        return item;
    }

    private JMenuItem createConnectItem() {
        JMenuItem connect = new JMenuItem("Connect");
        class OpenConnectionListener implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
            	if(!loggedIn) {
            		JOptionPane.showMessageDialog(null, "You have not Logged In yet",
                            "Please Log in first", JOptionPane.PLAIN_MESSAGE);
            	}
            	else {
                // TODO Auto-generated method stub
                try {
                    socket = new Socket("localhost", 8000);
                    chatField.append("connected" + "\n");
                    fromServer = new DataInputStream(socket.getInputStream());
                    new Thread(new HandleServer(fromServer)).start();
                    
                    try {

                        toServer = new DataOutputStream(socket.getOutputStream());
                    }
                    catch (IOException ex) {
                        chatField.append(ex.toString() + '\n');
                    }

                    try {
                        String sentMessage = tempUserName;
//                        chatField.append("id:"+sentMessage+"\n");
                        toServer.writeUTF(sentMessage);
                        toServer.flush();
                    }
                    catch (IOException ex) {
                        System.err.println(ex);
                    }
                    
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                    chatField.append("connection Failure");
                }
            }
            }

        }
        ActionListener listener = new OpenConnectionListener();
        connect.addActionListener(listener);
        return connect;
    }

    class ChatClient extends JFrame implements Runnable{
        String host = "localhost";
        public ChatClient(){
            Thread t = new Thread(this);
            t.start();

        }
        public JTextField textField() {
            class TextFieldListener implements ActionListener {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {

                        toServer = new DataOutputStream(socket.getOutputStream());
                    }
                    catch (IOException ex) {
                        chatField.append(ex.toString() + '\n');
                    }

                    try {
                        String sentMessage = sendText.getText();
//                        chatField.append("id:"+sentMessage+"\n");
                        toServer.writeUTF(sentMessage);
                        toServer.flush();
                    }
                    catch (IOException ex) {
                        System.err.println(ex);
                    }
                    sendText.setText("");
                }
            }
            sendText.addActionListener(new TextFieldListener());
            return sendText;
        }




        @Override
        public void run() {
            try {
                if(socket != null) {
                    fromServer = new DataInputStream(socket.getInputStream());
                    new Thread(new HandleServer(fromServer)).start();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    class HandleServer implements Runnable {
        private DataInputStream data; // A connected socket

        /** Construct a thread */
        public HandleServer(DataInputStream data) {
            this.data = data;
        }

        /** Run a thread */
        public void run() {
            try {
                while (true) {
                    String receivedMessage = data.readUTF();
                    chatField.append(receivedMessage + '\n');

                }
            }
            catch(IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    
    
    public static void main(String[] agrs)
    {
        AppGui a = new AppGui();
        a.setVisible(true);
    }
}