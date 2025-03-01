package View;
import Model.QA;
import Model.QASA;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

import static java.awt.Color.BLUE;
import static java.awt.Color.RED;

public class RoomSAView extends JFrame {
    private JFrame myMainFrame;
    private JTextArea myTaQuestion;
    private JTextField myTfInputAns;
    private JTextArea myTaHint;
    private JButton myBtnHints;
    private JButton myBtnSubmit;

    public boolean myCheckAns;
    private String myCate;
    private int myId;
    private String myCorrAns;
    private String myHint;
    private ArrayList<String> arrRedChoice;
    private ArrayList<String> arrOpt50;
    private QA myBank;

    // Constructor to setup GUI components and event handlers
    public RoomSAView(String theCate, int theId) {
        myCate = theCate;
        myId = theId;
        myBank = new QASA(theCate, theId);
        myHint = "";
        prepareGUI(theCate, theId);
        myCheckAns = false;
        roomShow();
    }
    public RoomSAView(){

    }

    private void prepareGUI(String theCate, int theId){
        myMainFrame = new JFrame("Welcome to challenge^^");
        myMainFrame.setSize(500,400);
        myMainFrame.setLayout(null);

        myTaQuestion = new JTextArea();
      /*  JScrollPane talkPane = new JScrollPane (myTaQuestion);
        talkPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        talkPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);*/

        myTaQuestion.setBounds(17,33,400,90);
        myTaQuestion.setText(displayQuestion(theCate, theId));
        myTaQuestion.setLineWrap(true);
        myTaQuestion.setWrapStyleWord(true);

        myTfInputAns = new JTextField();
        myTfInputAns.setBounds(20, 250, 200, 40);

        myTaHint = new JTextArea();
        myTaHint.setBounds(17, 150, 380, 60 );
        myTaHint.setVisible(false);

        myBtnHints = new JButton("Hints");
        myBtnHints.setBackground(BLUE);
        myBtnHints.setBounds(17, 8, 50,20);
        myBtnSubmit = new JButton("Submit");
        myBtnSubmit.setBounds(230, 330, 80, 30);
        myBtnSubmit.setBackground(RED);


        myMainFrame.add(myTaQuestion);
        myMainFrame.add(myBtnHints);
        myMainFrame.add(myTfInputAns);
        myMainFrame.add(myBtnSubmit);
        myMainFrame.add(myTaHint);

        myMainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                myMainFrame.dispose();
                //System.exit(0);
            }
        });


        showEventDemo();
    }

    public void roomShow(){
        myMainFrame.setVisible(true);
    }

    public void showEventDemo(){

        myBtnHints.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myHint = "Hints: ";
                myHint += displayHints(myCate, myId);
                myTaHint.setText(myHint);
                myTaHint.setVisible(true);
                //JOptionPane.showMessageDialog(myBtnHints,"Display Hints");
            }
        });

        myBtnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = "";
                String temp = myTfInputAns.getText();
                myCorrAns = displayAnswer(myCate, myId);
                if(temp.toUpperCase().equals(myCorrAns.toUpperCase())){
                    text = "It's correct. You're pass!";
                    RoomMCView.index++;
                    myCheckAns = true;
                }
                else{
                    text = "It's not correct. Please, try other door!";
                    myCheckAns = false;
                }
                JOptionPane.showMessageDialog(myBtnSubmit,text);
                myMainFrame.dispose();
            }
        });
    }


    public String displayQuestion(String theCate, int theId) {
        String ques = myBank.getQuestion(theCate, theId);
        //System.out.println(ques);
        return ques;
    }

    public String displayAnswer(String theCate, int theId) {
        String ans = myBank.getAnswer(theCate, theId);
        //System.out.println(ans);
        return ans;
    }

    public String displayHints(String theCate, int theId) {
        String hint = ((QASA) myBank).getHintSA(theCate, theId);
        return hint;
    }


}
