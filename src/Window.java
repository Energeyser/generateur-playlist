//fichier qui gere l'affichage de la fenetre principale

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class Window extends JFrame implements ActionListener{
    JList list;
    private JFileChooser fileChooser;
    private JScrollPane scrollPane;
    private JButton b_calculer;
    private JButton b_chooseFile;
    private JLabel jlbl_h = new JLabel("H :");
    private JLabel jlbl_min = new JLabel("M :");
    private JLabel jlbl_sec = new JLabel("S :");
    private JLabel jlbl_duree = new JLabel("Durée souhaitée pour la playlist :");
    private JTextField jtf_heures;
    private JTextField jtf_min;
    private JTextField jtf_sec;
    private XMLparse parser = new XMLparse();
    private int duree = 0;
    private File fichier = null;

    public Window ()
    {
        super ();
        setDefaultCloseOperation (WindowConstants.EXIT_ON_CLOSE);
        CreateElements ();
        this.setSize(500, 300);

       // pack ();
        setVisible (true);
    }

    private void CreateElements ()
    {
        getContentPane ().setLayout (new GridBagLayout ());
        GridBagConstraints constraints = new GridBagConstraints ();


        //creation du filechooser
        fileChooser = new JFileChooser();

        //bouton de choix du fichier
        b_chooseFile = new JButton("Choisir le fichier XML");
        constraints.gridx = 0; // Position x.
        constraints.gridy = 0; // Position y.
        constraints.gridwidth = 2; // Largeur.
        constraints.gridheight = 1; // Hauteur.
        getContentPane().add (b_chooseFile, constraints);
        b_chooseFile.addActionListener (this);


        //label duree
        constraints.gridx = 0; // Position x.
        constraints.gridy = 1; // Position y.
        constraints.gridwidth = 1; // Largeur.
        constraints.gridheight = 1; // Hauteur.
        getContentPane().add (jlbl_duree, constraints);

        //label heure
        constraints.gridx = 0; // Position x.
        constraints.gridy = 2; // Position y.
        constraints.gridwidth = 1; // Largeur.
        constraints.gridheight = 1; // Hauteur.
        getContentPane().add (jlbl_h, constraints);

        //TextField heures
        jtf_heures = new JTextField(10);
        constraints.gridx = 1; // Position x.
        constraints.gridy = 2; // Position y.
        constraints.gridwidth = 1; // Largeur.
        constraints.gridheight = 1; // Hauteur.
        getContentPane().add (jtf_heures, constraints);

        //label min
        constraints.gridx = 0; // Position x.
        constraints.gridy = 3; // Position y.
        constraints.gridwidth = 1; // Largeur.
        constraints.gridheight = 1; // Hauteur.
        getContentPane().add (jlbl_min, constraints);

        //TextField min
        jtf_min = new JTextField(10);
        constraints.gridx = 1; // Position x.
        constraints.gridy = 3; // Position y.
        constraints.gridwidth = 1; // Largeur.
        constraints.gridheight = 1; // Hauteur.
        getContentPane().add (jtf_min, constraints);

        //label sec
        constraints.gridx = 0; // Position x.
        constraints.gridy = 4; // Position y.
        constraints.gridwidth = 1; // Largeur.
        constraints.gridheight = 1; // Hauteur.
        getContentPane().add (jlbl_sec, constraints);

        //TextField sec
        jtf_sec = new JTextField(10);
        constraints.gridx = 1; // Position x.
        constraints.gridy = 4; // Position y.
        constraints.gridwidth = 1; // Largeur.
        constraints.gridheight = 1; // Hauteur.
        getContentPane().add (jtf_sec, constraints);

        //bouton calcul
        b_calculer = new JButton ("Calculer");
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0; // Position x.
        constraints.gridy = 5; // Position y.
        constraints.gridwidth = 2; // Largeur.
        constraints.gridheight = 1; // Hauteur.
        getContentPane ().add (b_calculer, constraints);
        b_calculer.addActionListener (this);


    }

    public void actionPerformed(ActionEvent event) {
        if(event.getSource() == b_calculer){
            System.out.println("click sur b_remove");
            try{
                //lors de l'appui sur le bouton calculer, on reccupere le contenu des textfields
                int heures = Integer.parseInt(jtf_heures.getText());
                int minutes = Integer.parseInt(jtf_min.getText());
                int secondes = Integer.parseInt(jtf_sec.getText());
                //puis on calcule la duree totale en ms
                int milli_hh = heures * 60 * 60 * 1000;
                int milli_min = minutes * 60 * 1000;
                int milli_sec = secondes * 1000;

                this.duree = milli_hh + milli_min + milli_sec; //duree totale en ms
            }
            catch (NumberFormatException e){
                //verification des exceptions
                javax.swing.JOptionPane.showMessageDialog(null,"Seuls les nombres sont autorisés");
            }
            if(fichier != null){
                //on verifie qu'il y a bien un fichier selectionne et on envoie la duree souhaitee au parser
                parser.Parse(this.duree, fichier);
               javax.swing.JOptionPane.showMessageDialog(null,"Merci de choisir un fichier");
            }

        }
        if(event.getSource() == b_chooseFile){
            //on filtre les fichiers affiches dans le filechooser pour n'avoir que les fichiers xml
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "Fichier XML", "xml");
            fileChooser.setFileFilter(filter);
            int returnVal = fileChooser.showOpenDialog(this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                this.fichier = fileChooser.getSelectedFile(); // le fichier choisi par l'utilisateur
            }
        }
    }
};