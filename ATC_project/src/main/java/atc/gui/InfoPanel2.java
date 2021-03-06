package atc.gui;

import atc.logic.Aircraft;
import javax.swing.JPanel;
import java.awt.Graphics;

import atc.logic.GameLogic;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

/**
 *
 * @author Kimmo
 * <p>
 * Luokka on paneeli, joka näyttää lentkoneiden aikataulutietoja ja laskeutuneet
 * ja epäonnistuneet lennot
 */
public class InfoPanel2 extends JPanel {

    private GameLogic gl;
    private Aircraft a;
    private int panelWidth;
    private int panelHeight;
    private Font font;

    /**
     * Konstruktori
     *
     * @param gameLogic GameLogic-olio
     * @param c paneelin väri
     * @param ph pääpaneelin korkeus
     */
    public InfoPanel2(GameLogic gameLogic, Color c, int ph) {
        font = new Font("Courier", Font.BOLD, 10);
        gl = gameLogic;

        panelHeight = ph / 3;
        panelWidth = ph / 3;

        initGamePanel(c);
    }

    private void initGamePanel(Color c) {
        setPreferredSize(new Dimension(panelWidth, panelHeight));
        setBackground(c);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawInfo(g);
    }

    private void drawInfo(Graphics g) {
        g.setFont(font);
        int textX = 10;
        int textY = 20;
        int rowHeight = 14;
        int rowCounter = 3;
        String eta;
        String[] entryPoint = {"NW ", "N  ", "E  ", "SW "};
        g.drawString("LANDED " + gl.getLanded() + "  FAILED " + gl.getLost(), textX, textY + (rowHeight * 0));
        g.drawString("SCHEDULE", textX, textY + (rowHeight * 2));
        g.drawString("ETA    ID       ENTRY   ALTITUDE", textX, textY + (rowHeight * 3));
        for (int[] flight : gl.getSchedule()) {
            rowCounter++;
            if ((flight[1] - gl.getScheduleClock()) / 10 * 10 >= 100) {
                eta = "" + (flight[1] - gl.getScheduleClock()) / 10 * 10;
            } else if ((flight[1] - gl.getScheduleClock()) / 10 * 10 >= 10) {
                eta = "0" + (flight[1] - gl.getScheduleClock()) / 10 * 10;
            } else {
                eta = "00" + (flight[1] - gl.getScheduleClock()) / 10 * 10;
            }
            g.drawString(eta + "    " + gl.getIdentifier(flight[0]) + "   " + entryPoint[flight[2]] + "     FL" + flight[3], textX, textY + (rowHeight * rowCounter));
            if (panelHeight - (textY + (rowHeight * rowCounter)) < 20) {
                break;
            }
        }
    }

    /**
     * metodi päivittää paneelin
     */
    public void drawUpdate() {
        this.repaint();
    }
}
