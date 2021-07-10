import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.*;
import javax.swing.Timer;

public class Gameplay extends JPanel implements KeyListener, ActionListener {

  private boolean play = false;
  private int score = 0;

  private int totalBricks = 48;

  private Timer timer;
  private int delay = 8;

  private int playerX = 310;

  private int ballposX = 120;
  private int ballposY = 350;
  private int ballXdir = -1;
  private int ballYdir = -2;

  public Gameplay() {
    addKeyListener(this);
    setFocusable(true);
    setFocusTraversalKeysEnabled(false);
    timer = new Timer(delay, this);
    timer.start();
  }

  public void paint(Graphics g) {
    // background
    g.setColor(Color.black);
    g.fillRect(1, 1, 692, 592);

    // borders
    g.setColor(Color.blue);
    g.fillRect(0, 0, 3, 592);
    // g.fillRect(681, 0, 3, 592);
    g.fillRect(0, 0, 692, 3);
    g.fillRect(691, 0, 3, 592);

    // pad
    g.setColor(Color.blue);
    g.fillRect(playerX, 550, 100, 8);

    // oval
    g.setColor(Color.orange);
    g.fillOval(ballposX, ballposY, 20, 20);

    g.dispose();
  }

  public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
      if (playerX >= 600) {
        playerX = 600;
      } else {
        moveRight();
      }
    }
    if (e.getKeyCode() == KeyEvent.VK_LEFT) {
      if (playerX < 10) {
        playerX = 10;
      } else {
        moveLeft();
      }
    }
  }

  public void keyTyped(KeyEvent e) {}

  public void keyReleased(KeyEvent e) {}

  public void moveRight() {
    play = true;
    playerX += 20;
  }

  public void moveLeft() {
    play = true;
    playerX -= 20;
  }

  public void actionPerformed(ActionEvent e) {
    timer.start();
    repaint();
  }
}
