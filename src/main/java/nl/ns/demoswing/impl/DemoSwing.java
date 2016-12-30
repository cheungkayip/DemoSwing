package nl.ns.demoswing.impl;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DemoSwing extends JFrame {
    private static final String gapList[] = {"0", "10", "15", "20"};
    private final static int maxGap = 20;

    private static ArrayList<String> itemList = new ArrayList<String>();
    private GridLayout gridLayout1 = new GridLayout(0,2);
    private GridLayout gridLayout2 = new GridLayout(0,3);
    private GridLayout gridLayout3 = new GridLayout(8,2);
    private GridLayout gridLayout4 = new GridLayout(0,2);
    private JFrame jFrame = new JFrame("GridLayout Demo Swing Application");

    private JPanel jPanel1 = new JPanel(); // For the JLabel, JTextfields, JButtons
    private JPanel jPanel2 = new JPanel(); // for the JComboxbox apply gaps
    private JPanel jPanel3 = new JPanel(); // For JCheckbox
    private JPanel jPanel4 = new JPanel(); // For JList

    private JComboBox horGapComboBox;
    private JComboBox verGapComboBox;

    private JLabel label1Button = new JLabel("Button 1: ");
    private JLabel label2Button = new JLabel("Button 2: ");
    private JLabel label3Button = new JLabel("Button 3: ");
    private JLabel label4Button = new JLabel("Button 4: ");
    private JLabel label5Button = new JLabel("Button 5: ");
    private JLabel resultLabel = new JLabel("Result: ");
    private JLabel label1Checkbox = new JLabel("Checkbox 1: ");
    private JLabel label2Checkbox = new JLabel("Checkbox 2: ");
    private JLabel label3Checkbox = new JLabel("Checkbox 3: ");
    private JLabel label1RadioButton = new JLabel("RadioButton 1: ");
    private JLabel label2RadioButton = new JLabel("RadioButton 2: ");
    private JLabel label3RadioButton = new JLabel("RadioButton 3: ");

    private JButton button1 = new JButton("Button1");
    private JButton button2 = new JButton("Button2");
    private JButton button3 = new JButton("Button3");
    private JButton button4 = new JButton("Long-Named Button 4");
    private JButton button5 = new JButton("Button5");
    private JButton applyButton = new JButton("Apply gaps");
    private JButton fakeButton = new JButton("Just fake button");

    private JTextField field1 = new JTextField("Result");

    private JCheckBox checkbox1 = new JCheckBox("Checkbox1");
    private JCheckBox checkbox2 = new JCheckBox("Checkbox2");
    private JCheckBox checkbox3 = new JCheckBox("Checkbox3");

    private JRadioButton radiobutton1 = new JRadioButton("RadioButton1");
    private JRadioButton radiobutton2 = new JRadioButton("RadioButton2");
    private JRadioButton radiobutton3 = new JRadioButton("RadioButton3");

    DefaultListModel lm = new DefaultListModel();
    private JList list = new JList(lm);


    private DemoSwing(String name) {
        super(name);
        setResizable(false);
    }

    private void initGaps() {
        horGapComboBox = new JComboBox(gapList);
        verGapComboBox = new JComboBox(gapList);
        horGapComboBox.setName("horGap");
        verGapComboBox.setName("verGap");
    }

    private void createButtonListener(JButton buttonObject) {
        final String someText = "You have clicked " + buttonObject.getText();

        buttonObject.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                field1.setText("");
                field1.setText(someText);
            }
        });

    }

    public ListSelectionListener listListener() {
        ListSelectionListener lsl = new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                boolean adjust = listSelectionEvent.getValueIsAdjusting();
                if (!adjust) {
                    JList list = (JList) listSelectionEvent.getSource();
                    int selections[] = list.getSelectedIndices();
                    Object selectionValues[] = list.getSelectedValues();
                    for (int i = 0, n = selections.length; i < n; i++) {
                        field1.setText("");
                        field1.setText("You have selected " + selectionValues[i]);
                        System.out.println("  Selections: " + selections[i] + "/" + selectionValues[i] + " ");
                    }
                }
            }
        };
        return lsl;
    }

    private void checkboxListener(final JCheckBox checkbox) {
        final String checkboxSelectedText = "You have selected " + checkbox.getText();
        final String checkboxUnselectedText = "You have Unselected " + checkbox.getText();
        checkbox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(checkbox.isSelected()){
                    field1.setText("");
                    field1.setText(checkboxSelectedText);
                }else{
                    field1.setText("");
                    field1.setText(checkboxUnselectedText);
                }

            }
        });
    }

    private void radioButtonListener(final JRadioButton radiobutton) {
        final String radioSelectedText = "You have selected " + radiobutton.getText();
        final String radioUnselectedText = "You have Unselected " + radiobutton.getText();
        radiobutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(radiobutton.isSelected()){
                    field1.setText("");
                    field1.setText(radioSelectedText);
                }else{
                    field1.setText("");
                    field1.setText(radioUnselectedText);
                }

            }
        });
    }

    private void applyGapsListener(JButton applyButton) {
        //Process the Apply gaps button press
        applyButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //Get the horizontal gap value
                String horGap = (String)horGapComboBox.getSelectedItem();
                //Get the vertical gap value
                String verGap = (String)verGapComboBox.getSelectedItem();
                //Set up the horizontal gap value

                gridLayout1.setHgap(Integer.parseInt(horGap));
                //Set up the vertical gap value
                gridLayout1.setVgap(Integer.parseInt(verGap));
                //Set up the layout of the buttons
                gridLayout1.layoutContainer(jPanel1);
                field1.setText("");
                field1.setText("You have set the Hgap: " + gridLayout1.getHgap() + " and the Vgap to " + gridLayout1.getVgap());
            }
        });
    }

    private void addSomeElementsToList(DefaultListModel lm) {
            for (int i = 0; i <= 8; i++) {
                itemList.add("Item " + i);
                lm.addElement(itemList.get(i));
            }
        }

    private void addComponentsToPane(final Container pane) {
        initGaps();
        jPanel1.setLayout(gridLayout1);
        jPanel2.setLayout(gridLayout2);
        jPanel3.setLayout(gridLayout3);
        jPanel4.setLayout(gridLayout4);

        //Set up components preferred size
        Dimension buttonSize = fakeButton.getPreferredSize();
        jPanel1.setPreferredSize(new Dimension((int)(buttonSize.getWidth() * 2.5)+maxGap,
                (int)(buttonSize.getHeight() * 3.5)+maxGap * 2));

        //Add labels + buttons to experiment with Grid Layout
        jPanel1.add(label1Button);
        jPanel1.add(label2Button);
        jPanel1.add(label3Button);
        jPanel1.add(label4Button);
        jPanel1.add(label5Button);

        jPanel1.add(button1);
        jPanel1.add(button2);
        jPanel1.add(button3);
        jPanel1.add(button4);
        jPanel1.add(button5);

        jPanel3.add(resultLabel);
        jPanel3.add(field1);

        //Add controls to set up horizontal and vertical gaps
        jPanel2.add(new Label("Horizontal gap:"));
        jPanel2.add(new Label("Vertical gap:"));
        jPanel2.add(new Label(" "));
        jPanel2.add(horGapComboBox);
        jPanel2.add(verGapComboBox);
        jPanel2.add(applyButton);

        jPanel3.add(label1Checkbox);
        jPanel3.add(checkbox1);
        jPanel3.add(label2Checkbox);
        jPanel3.add(checkbox2);
        jPanel3.add(label3Checkbox);
        jPanel3.add(checkbox3);

        jPanel3.add(label1RadioButton);
        jPanel3.add(radiobutton1);
        jPanel3.add(label2RadioButton);
        jPanel3.add(radiobutton2);
        jPanel3.add(label3RadioButton);
        jPanel3.add(radiobutton3);

        addSomeElementsToList(lm);
        list.setName("List");
        jPanel4.add(list);


        // Add the Lists to some listeners
        list.addListSelectionListener(listListener());

        applyGapsListener(applyButton); // Do some action with Apply Gaps button

        createButtonListener(button1); // Add the Button Listeners to the application
        createButtonListener(button2);
        createButtonListener(button3);
        createButtonListener(button4);
        createButtonListener(button5);

        checkboxListener(checkbox1); // Add the checkbox Listeners to the Application
        checkboxListener(checkbox2);
        checkboxListener(checkbox3);

        radioButtonListener(radiobutton1);
        radioButtonListener(radiobutton2);
        radioButtonListener(radiobutton3);

        pane.add(jPanel1, BorderLayout.NORTH);
//        pane.add(new JSeparator(), BorderLayout.CENTER);
        pane.add(jPanel2, BorderLayout.SOUTH);
        pane.add(jPanel3, BorderLayout.CENTER);
        pane.add(jPanel4, BorderLayout.EAST);

        setPreferredSize(new Dimension(800,600));
        System.out.println("ArrayList itemSize: " + itemList.size());
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method is invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        DemoSwing frame = new DemoSwing("Demo Swing Application");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //Set up the content pane.
        frame.addComponentsToPane(frame.getContentPane());
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) throws UnsupportedLookAndFeelException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        /* Use an appropriate Look and Feel */
        //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        /* Turn off metal's use of bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);

        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();

            }
        });
    }
}