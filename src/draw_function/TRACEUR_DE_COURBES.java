/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package draw_function;

/**
 *
 * @author walide
 */
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import draw_function.functions;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.script.ScriptException;
import javax.swing.JColorChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class TRACEUR_DE_COURBES extends javax.swing.JFrame {

    Graphics2D g;
    int zom = 0;
    int dpi = Toolkit.getDefaultToolkit().getScreenResolution();
    functions f = new functions();
    Color c;

    /**
     * Creates new form TRACEUR_DE_COURBES
     */
    public TRACEUR_DE_COURBES() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        initComponents();
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        g = (Graphics2D) panel_corb.getGraphics();

    }

    public void print(int zoom, Graphics2D g) {
        int i = 0;
        int j = 0;
        g.translate(panel_corb.getWidth() / 2, panel_corb.getHeight() / 2);
        while (i <= panel_corb.getWidth() / 2) {
            g.drawLine(i, 0, i, -10);
            g.drawString("" + j, i, 11);
            i = (int) (i + (dpi / 2.54));
            j = j + zoom;
        }
        i = 0;
        j = 0;
        while (i > -(panel_corb.getWidth() / 2)) {
            g.drawLine(i, 0, i, -10);
            if (j != 0) {
                g.drawString("-" + j, i, 11);
            }
            i = (int) (i - (dpi / 2.54));
            j = j + zoom;
        }
        i = 0;
        j = 0;
        while (i >= -(panel_corb.getHeight() / 2)) {
            g.drawLine(0, i, 10, i);
            if (j != 0) {
                g.drawString("" + j, 10, i);
            }
            i = (int) (i - (dpi / 2.54));
            j = j + zoom;
        }
        i = 0;
        j = 0;
        while (i <= panel_corb.getHeight() / 2) {
            g.drawLine(0, i, 11, i);
            if (j != 0) {
                g.drawString("-" + j, 10, i);
            }
            i = (int) (i + (dpi / 2.54));
            j = j + zoom;
        }
        g.setColor(Color.red);
        g.drawLine(-(panel_corb.getWidth() / 2), 0, panel_corb.getWidth() / 2, 0);
        g.drawLine(0, -(panel_corb.getHeight() / 2), 0, panel_corb.getHeight() / 2);
    }

    public void change_panel_size() {
        double minx;
        double maxx;
        double miny;
        double maxy;
        if (minx_txt.getText().isEmpty()) {
            minx = -(panel_corb.getWidth() / 2);
        } else {
            minx = Double.parseDouble(minx_txt.getText());
        }
        if (maxx_txt.getText().isEmpty()) {
            maxx = (panel_corb.getWidth() / 2);
        } else {
            maxx = Double.parseDouble(maxx_txt.getText());
        }
        if (miny_txt.getText().isEmpty()) {
            miny = -(panel_corb.getHeight() / 2);
        } else {
            miny = Double.parseDouble(miny_txt.getText());
        }
        if (maxy_txt.getText().isEmpty()) {
            maxy = (panel_corb.getHeight() / 2);
        } else {
            maxy = Double.parseDouble(maxy_txt.getText());
        }
        int width = (int) (maxx * (dpi / 2.54) - minx * (dpi / 2.54));
        int height = (int) (maxy * (dpi / 2.54) - miny * (dpi / 2.54));
        Dimension d = new Dimension(width, height);
        panel_corb.setPreferredSize(d);
        panel_corb.setSize(d);

    }

//__________________________ LA FOCTION DE PAINT CORB ________________________________
    public void paint_corb(int z) throws ScriptException {

        double j1 = 0, j2;
        double i2 = 0;
        String exp = "";
        String x1 = "";
        String x2 = "";
        double minx;
        double maxx;
        double miny;
        double maxy;
        if (minx_txt.getText().isEmpty()) {
            minx = -((panel_corb.getWidth() / 2));
        } else {
            minx = Double.parseDouble(minx_txt.getText());
        }
        if (maxx_txt.getText().isEmpty()) {
            maxx = (panel_corb.getWidth() / 2);
        } else {
            maxx = Double.parseDouble(maxx_txt.getText());
        }
        if (miny_txt.getText().isEmpty()) {
            miny = -(panel_corb.getHeight() / 2);
        } else {
            miny = Double.parseDouble(miny_txt.getText());
        }
        if (maxy_txt.getText().isEmpty()) {
            maxy = (panel_corb.getHeight() / 2);
        } else {
            maxy = Double.parseDouble(maxy_txt.getText());
        }

        if (fonction_txt.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Entrer votre fonction SVP !!");
        } else {
            print(z, g);
            Graphics2D courb = (Graphics2D) panel_corb.getGraphics();
            courb.setColor(c);
            String corr_exp = f.correct_string(fonction_txt.getText());
            exp = corr_exp;
            System.out.println("exp=" + exp);
            courb.translate(panel_corb.getWidth() / 2, panel_corb.getHeight() / 2);

            double i = minx;
            while (i <= maxx) {
                i2 = i + 0.1;
                x1 = f.cherche_x(exp, i);
                System.out.println("x1= " + x1);
                x2 = f.cherche_x(exp, i2);
                System.out.println("x2= " + x2);
                j1 = -(f.exprition(x1));
                j2 = -(f.exprition(x2));

                System.out.println("j1= " + j1);

                System.out.println("j2= " + j2);
                if (j1 <= -(miny) && j1 >= -(maxy)) {
                    if (j2 <= -miny + 0.1 && j2 >= -maxy + 0.1) {
                        Shape l;
                        l = new Line2D.Double((i) * (dpi / 2.54), (j1) * (dpi / 2.54), (i2) * (dpi / 2.54), (j2) * (dpi / 2.54));
                        courb.draw(l);
                    }

                }
                i = i2;

            }

            System.out.println("whidth=" + (int) (maxx * (dpi / 2.54) - minx * (dpi / 2.54)) + "  heighth" + (int) (maxy * (dpi / 2.54) - miny * (dpi / 2.54)));
            System.out.println("boucle maxy=" + maxy + "  miny" + miny + "zoom = " + z);

        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        jRadioButtonMenuItem1 = new javax.swing.JRadioButtonMenuItem();
        jRadioButtonMenuItem2 = new javax.swing.JRadioButtonMenuItem();
        panel_cmd = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        fonction_txt = new javax.swing.JTextField();
        minx_txt = new javax.swing.JTextField();
        maxx_txt = new javax.swing.JTextField();
        Execution = new javax.swing.JButton();
        maxy_txt = new javax.swing.JTextField();
        miny_txt = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        sld_zoom = new javax.swing.JSlider();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        panel_corb = new javax.swing.JPanel();

        jRadioButtonMenuItem1.setSelected(true);
        jRadioButtonMenuItem1.setText("jRadioButtonMenuItem1");

        jRadioButtonMenuItem2.setSelected(true);
        jRadioButtonMenuItem2.setText("jRadioButtonMenuItem2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("TRACEUR DE CORBS");
        setPreferredSize(new java.awt.Dimension(1025, 550));
        setResizable(false);
        setSize(new java.awt.Dimension(1025, 550));
        getContentPane().setLayout(null);

        panel_cmd.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 2, 1, 2, new java.awt.Color(0, 0, 0)));
        panel_cmd.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setText("ESPACE DES COMMANDES");
        jLabel1.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 0, 2, 0, new java.awt.Color(0, 0, 255)));
        panel_cmd.add(jLabel1);
        jLabel1.setBounds(40, 10, 240, 40);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("FONCTION :");
        panel_cmd.add(jLabel2);
        jLabel2.setBounds(10, 260, 80, 20);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("MIN X :");
        panel_cmd.add(jLabel3);
        jLabel3.setBounds(10, 120, 50, 20);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("MAX X :");
        panel_cmd.add(jLabel4);
        jLabel4.setBounds(140, 120, 50, 20);

        fonction_txt.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        fonction_txt.setText("x^2");
        panel_cmd.add(fonction_txt);
        fonction_txt.setBounds(10, 280, 250, 40);

        minx_txt.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        panel_cmd.add(minx_txt);
        minx_txt.setBounds(10, 140, 110, 40);

        maxx_txt.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        maxx_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maxx_txtActionPerformed(evt);
            }
        });
        panel_cmd.add(maxx_txt);
        maxx_txt.setBounds(140, 140, 120, 40);

        Execution.setBackground(new java.awt.Color(204, 204, 255));
        Execution.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        Execution.setForeground(new java.awt.Color(51, 255, 0));
        Execution.setText("Execution");
        Execution.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExecutionActionPerformed(evt);
            }
        });
        panel_cmd.add(Execution);
        Execution.setBounds(70, 330, 120, 40);

        maxy_txt.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        panel_cmd.add(maxy_txt);
        maxy_txt.setBounds(140, 210, 120, 40);

        miny_txt.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        panel_cmd.add(miny_txt);
        miny_txt.setBounds(10, 210, 110, 40);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("MIN Y :");
        panel_cmd.add(jLabel5);
        jLabel5.setBounds(10, 190, 50, 20);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("MAX Y:");
        panel_cmd.add(jLabel6);
        jLabel6.setBounds(140, 190, 50, 20);

        sld_zoom.setMinimum(1);
        sld_zoom.setValue(0);
        sld_zoom.setName(""); // NOI18N
        sld_zoom.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sld_zoomStateChanged(evt);
            }
        });
        sld_zoom.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sld_zoomMouseReleased(evt);
            }
        });
        panel_cmd.add(sld_zoom);
        sld_zoom.setBounds(70, 390, 240, 26);

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 51, 255));
        jLabel7.setText("ZOOM");
        panel_cmd.add(jLabel7);
        jLabel7.setBounds(10, 390, 60, 30);

        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton1.setText("selectionner la coleur");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        panel_cmd.add(jButton1);
        jButton1.setBounds(50, 70, 200, 30);

        getContentPane().add(panel_cmd);
        panel_cmd.setBounds(10, 30, 320, 460);

        panel_corb.setBackground(new java.awt.Color(255, 255, 255));
        panel_corb.setBorder(new javax.swing.border.MatteBorder(null));
        panel_corb.setAutoscrolls(true);
        jScrollPane1.setViewportView(panel_corb);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(350, 30, 640, 470);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ExecutionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExecutionActionPerformed
      
            try {
                paint_corb(1);
            } catch (ScriptException ex) {
                JOptionPane.showMessageDialog(this, "virifier votre exprission SVP !!\n tu peut aussi prondre une vue sur les foctions supporter par notre programe");
            }
        
    }//GEN-LAST:event_ExecutionActionPerformed

    private void sld_zoomStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sld_zoomStateChanged

        print(zom + sld_zoom.getValue(), g);        // TODO add your handling code here:

    }//GEN-LAST:event_sld_zoomStateChanged

    private void sld_zoomMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sld_zoomMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_sld_zoomMouseReleased

    private void maxx_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maxx_txtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_maxx_txtActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JColorChooser jc = new JColorChooser();
        c = jc.showDialog(null, "select color", Color.BLACK);
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TRACEUR_DE_COURBES.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TRACEUR_DE_COURBES.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TRACEUR_DE_COURBES.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TRACEUR_DE_COURBES.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                TRACEUR_DE_COURBES tc = null;
                try {
                    tc = new TRACEUR_DE_COURBES();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(TRACEUR_DE_COURBES.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InstantiationException ex) {
                    Logger.getLogger(TRACEUR_DE_COURBES.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(TRACEUR_DE_COURBES.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(TRACEUR_DE_COURBES.class.getName()).log(Level.SEVERE, null, ex);
                }
                tc.setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Execution;
    private javax.swing.JTextField fonction_txt;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField maxx_txt;
    private javax.swing.JTextField maxy_txt;
    private javax.swing.JTextField minx_txt;
    private javax.swing.JTextField miny_txt;
    private javax.swing.JPanel panel_cmd;
    private javax.swing.JPanel panel_corb;
    private javax.swing.JSlider sld_zoom;
    // End of variables declaration//GEN-END:variables
}
