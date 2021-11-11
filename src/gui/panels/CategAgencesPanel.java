package gui.panels;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * Panel permettant de trouver les agences ayant toutes les categories de vehicule
 * Utilisation du patron singleton
 *
 * @author arnoux23u
 * @author steiner58u
 */
public class CategAgencesPanel extends JPanel {

    /**
     * Instance singleton
     */
    private static CategAgencesPanel instance;

    /**
     * Container panel central
     */
    private Container displayed;

    /**
     * Panel result
     */
    private final JPanel panel1;

    /**
     * Constructeur prive
     */
    private CategAgencesPanel() {
        setPreferredSize(new Dimension(889, 730));
        setLayout(new BorderLayout());

        //Labels
        JLabel label1 = new JLabel("Agences toutes cat\u00e9gories") {{
            setHorizontalAlignment(SwingConstants.CENTER);
            setFont(getFont().deriveFont(getFont().getStyle() & ~Font.BOLD, getFont().getSize() + 10f));

        }};

        //Panels
        panel1 = new JPanel() {{
            setLayout(new BorderLayout());
            setPreferredSize(new Dimension(200, 200));
            setBorder(new EmptyBorder(100, 20, 20, 20));
        }};

        //Containers
        displayed = new JLabel("Recherche en cours ...") {{
            setFont(new Font("Tahoma", Font.BOLD, 18));
            setHorizontalAlignment(CENTER);
        }};

        add(label1, BorderLayout.PAGE_START);
        panel1.add(displayed, BorderLayout.CENTER);
        add(panel1, BorderLayout.CENTER);
    }

    /**
     * Getter instance panel
     *
     * @return panel
     */
    public static synchronized CategAgencesPanel getInstance() {
        if (instance == null) {
            instance = new CategAgencesPanel();
        }
        return instance;
    }

    /**
     * Methode actualisation resultat
     */
    public void actualise() {
        panel1.removeAll();
        String[][] data;
        String[] headers;
        /*
        TODO NOE
            Recuperer toutes les colonnes et toutes les valeurs de la Q4 et les stocker dans un tableau sous cette forme
            Exemple :
            String[] headers = {"Nom","Prenom"};
            String[][] data = {{"BIGRON","Steven"},{"ST1NERE","Noere"},{"MISKINE","Anto"},{"V","Thomthom"}};
         */


        headers = new String[]{"Nom", "Prenom", "Adresse", "Note"};
        data = new String[][]{{"BIGRON", "Steven", "Rue du reveil", "ABSENT"}, {"ST1NERE", "Noere", "Rue du malade", "MORT"}, {"MISKINE", "Anto", "Rue du $magik", "FAUX ACCENT"}, {"V", "Thomthom", "Rue du Java", "THREAD"}};


        displayed = new JTable(data, headers) {{
            setPreferredSize(new Dimension(100, 100));
            setFont(new Font("Tahoma", Font.PLAIN, 20));
            setRowHeight(40);
            setEnabled(false);
        }};
        panel1.add(((JTable) displayed).getTableHeader(), BorderLayout.NORTH);
        panel1.add(displayed, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
}
