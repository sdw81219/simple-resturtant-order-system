import javafx.scene.layout.Border;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        chatField();
        OrderCheck();
        textCheck();


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
                if(true){
                    JOptionPane.showMessageDialog(null, "Successfully login",
                            null, JOptionPane.PLAIN_MESSAGE);
                }
                else{
                    JOptionPane.showMessageDialog(null, "No such an account, please register a new account",
                            null, JOptionPane.PLAIN_MESSAGE);
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
        JLabel firstDish = new JLabel("Gongbao Chicken     $30");
//        JLabel firstDishPic = new JLabel();
        ImageIcon gongbao =new ImageIcon ("img//1.jpg");
        gongbao.setImage(gongbao.getImage().getScaledInstance(150,100,Image.SCALE_DEFAULT));
        //若是单独把图片放到一个label中，则图片会显示在右边
//        firstDishPic.setIcon(gongbao);
        firstDish.setIcon(gongbao);
        JComboBox firstAmount =new JComboBox();
        firstAmount.addItem("1");
        firstAmount.addItem("2");
        firstAmount.addItem("3");
        firstAmount.addItem("4");
        firstAmount.addItem("5");
        firstPanel.add(firstDish);
        firstPanel.add(firstAmount);
        //        firstPanel.add(firstDishPic);

        JPanel secondPanel = new JPanel();
        JLabel secondDish = new JLabel("Ban Mian     $25");
        secondPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        ImageIcon banMian =new ImageIcon ("img//2.jpg");
        banMian.setImage(banMian.getImage().getScaledInstance(150,100,Image.SCALE_DEFAULT));
        secondDish.setIcon(banMian);
        JComboBox secondAmount =new JComboBox();
        secondAmount.addItem("1");
        secondAmount.addItem("2");
        secondAmount.addItem("3");
        secondAmount.addItem("4");
        secondAmount.addItem("5");
        secondPanel.add(secondDish);
        secondPanel.add(secondAmount);

        JPanel thirdPanel = new JPanel();
        JLabel thirdDish = new JLabel("haiDai     $10");
        thirdPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        ImageIcon haiDai =new ImageIcon ("img//3.jpg");
        haiDai.setImage(haiDai.getImage().getScaledInstance(150,100,Image.SCALE_DEFAULT));
        thirdDish.setIcon(haiDai);
        JComboBox thirdAmount =new JComboBox();
        thirdAmount.addItem("1");
        thirdAmount.addItem("2");
        thirdAmount.addItem("3");
        thirdAmount.addItem("4");
        thirdAmount.addItem("5");
        thirdPanel.add(thirdDish);
        thirdPanel.add(thirdAmount);

        JPanel forthPanel = new JPanel();
        JLabel forthDish = new JLabel("Banli Cake     $15");
        forthPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        ImageIcon BanliCake =new ImageIcon ("img//4.jpg");
        BanliCake.setImage(BanliCake.getImage().getScaledInstance(150,100,Image.SCALE_DEFAULT));
        forthDish.setIcon(BanliCake);
        JComboBox forthAmount =new JComboBox();
        forthAmount.addItem("1");
        forthAmount.addItem("2");
        forthAmount.addItem("3");
        forthAmount.addItem("4");
        forthAmount.addItem("5");
        forthPanel.add(forthDish);
        forthPanel.add(forthAmount);


        JPanel fifthPanel = new JPanel();
        JLabel fifthDish = new JLabel("Ice cream     $13");
        fifthPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        ImageIcon IceCream =new ImageIcon ("img//5.jpg");
        IceCream.setImage(IceCream.getImage().getScaledInstance(150,100,Image.SCALE_DEFAULT));
        fifthDish.setIcon(IceCream);
        JComboBox fifthAmount =new JComboBox();
        fifthAmount.addItem("1");
        fifthAmount.addItem("2");
        fifthAmount.addItem("3");
        fifthAmount.addItem("4");
        fifthAmount.addItem("5");
        fifthPanel.add(fifthDish);
        fifthPanel.add(fifthAmount);

        JPanel sixPanel = new JPanel();
        JLabel sixDish = new JLabel("Appetizer     $35");
        sixPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        ImageIcon appetizer =new ImageIcon ("img//6.jpg");
        appetizer.setImage(appetizer.getImage().getScaledInstance(150,100,Image.SCALE_DEFAULT));
        sixDish.setIcon(appetizer);
        JComboBox sixAmount =new JComboBox();
        sixAmount.addItem("1");
        sixAmount.addItem("2");
        sixAmount.addItem("3");
        sixAmount.addItem("4");
        sixAmount.addItem("5");
        sixPanel.add(sixDish);
        sixPanel.add(sixAmount);



        JPanel sevenPanel = new JPanel();
        JLabel sevenDish = new JLabel("Chiese BBQ     $35");
        sevenPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        ImageIcon ChineseBBQ =new ImageIcon ("img//7.jpg");
        ChineseBBQ.setImage(ChineseBBQ.getImage().getScaledInstance(150,100,Image.SCALE_DEFAULT));
        sevenDish.setIcon(ChineseBBQ);
        JComboBox sevenAmount =new JComboBox();
        sevenAmount.addItem("1");
        sevenAmount.addItem("2");
        sevenAmount.addItem("3");
        sevenAmount.addItem("4");
        sevenAmount.addItem("5");
        sevenPanel.add(sevenDish);
        sevenPanel.add(sevenAmount);

        JPanel eightPanel = new JPanel();
        JLabel eightDish = new JLabel("Pizza    $25");
        eightPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        ImageIcon Pizza =new ImageIcon ("img//8.jpg");
        Pizza.setImage(Pizza.getImage().getScaledInstance(150,100,Image.SCALE_DEFAULT));
        eightDish.setIcon(Pizza);
        JComboBox eightAmount =new JComboBox();
        eightAmount.addItem("1");
        eightAmount.addItem("2");
        eightAmount.addItem("3");
        eightAmount.addItem("4");
        eightAmount.addItem("5");
        eightPanel.add(eightDish);
        eightPanel.add(eightAmount);


        JPanel ninePanel = new JPanel();
        JLabel nineDish = new JLabel("Spaghetti    $20");
        ninePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        ImageIcon Spaghetti =new ImageIcon ("img//9.jpg");
        Spaghetti.setImage(Spaghetti.getImage().getScaledInstance(150,100,Image.SCALE_DEFAULT));
        nineDish.setIcon(Spaghetti);
        JComboBox nineAmount =new JComboBox();
        nineAmount.addItem("1");
        nineAmount.addItem("2");
        nineAmount.addItem("3");
        nineAmount.addItem("4");
        nineAmount.addItem("5");
        ninePanel.add(nineDish);
        ninePanel.add(nineAmount);

        JPanel tenPanel = new JPanel();
        JLabel tenDish = new JLabel("Sushi    $28");
        tenPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        ImageIcon sushi =new ImageIcon ("img//10.jpg");
        sushi.setImage(sushi.getImage().getScaledInstance(150,100,Image.SCALE_DEFAULT));
        tenDish.setIcon(sushi);
        JComboBox tenAmount =new JComboBox();
        tenAmount.addItem("1");
        tenAmount.addItem("2");
        tenAmount.addItem("3");
        tenAmount.addItem("4");
        tenAmount.addItem("5");
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


    private void chatField(){
        JPanel chatPanel = new JPanel();
        chatPanel.setLayout(new BoxLayout(chatPanel, BoxLayout.Y_AXIS));
        JTextArea chatField = new JTextArea();
        scrollPane1 = new JScrollPane(chatField);
        scrollPane1.setPreferredSize(new Dimension(350,580));
        chatPanel.add(scrollPane1);
        JTextField sendText = new JTextField();
        sendText.setPreferredSize(new Dimension(350,70));
        chatPanel.add(sendText);
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
        JLabel label2 = new JLabel("Welcome:Username.....");
        if(false){
            setJMenuBar(menuBar);
            menuBar.add(Box.createHorizontalGlue());
            menuBar.add(label2);

        }
        else{
            label2.setVisible(false);
        }

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
                JOptionPane.showMessageDialog(null, "Thanks for your order!!",
                        "ENJOY YOUR FOOD", JOptionPane.PLAIN_MESSAGE);
//                JFrame OrderPopup =new JFrame();
//                OrderPopup.setSize(200,100);
//                JPanel PopupPanel = new JPanel();
//                JLabel OrderSucc = new JLabel("Thanks for your order!!");
//
//                OrderPopup.add(PopupPanel,BorderLayout.CENTER);
//                PopupPanel.add(OrderSucc);
//                OrderPopup.setVisible(true);
            }
        });

        botPanel.add(orderPanel);


    }

    private void textCheck(){
        JPanel textPanel = new JPanel();
        JButton textCheck = new JButton("send");
        textCheck.setPreferredSize(new Dimension(200,50));
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
        JMenuItem item = new JMenuItem("Connect");
        //make a connection with server;So they can chat with server;


        return item;
    }



    public static void main(String[] agrs)
    {
        AppGui a = new AppGui();
        a.setVisible(true);
    }
}






