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
    requestFocus();
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
    g.fillRect(0, 0, 692, 3);
    g.fillRect(691, 0, 3, 592);

    // score
    g.setColor(Color.white);
    g.setFont(new Font("serif", Font.BOLD, 25));
    g.drawString("" + score, 590, 30);

    // pad
    g.setColor(Color.blue);
    g.fillRect(playerX, 550, 100, 8);

    // ball
    g.setColor(Color.orange);
    g.fillOval(ballposX, ballposY, 20, 20);

    // win
    if (totalBricks <= 0) {
      play = false;
      ballXdir = 0;
      ballYdir = 0;
      g.setColor(Color.RED);
      g.setFont(new Font("serif", Font.BOLD, 30));
      g.drawString("You Won", 260, 300);

      g.setColor(Color.RED);
      g.setFont(new Font("serif", Font.BOLD, 20));
      g.drawString("Press (Enter) to Restart", 230, 350);
    }

    // when you lose the game
    if (ballposY > 570) {
      play = false;
      ballXdir = 0;
      ballYdir = 0;
      g.setColor(Color.RED);
      g.setFont(new Font("serif", Font.BOLD, 30));
      g.drawString("Game Over, Scores: " + score, 190, 300);

      g.setColor(Color.RED);
      g.setFont(new Font("serif", Font.BOLD, 20));
      g.drawString("Press (Enter) to Restart", 230, 350);
    }

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
    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
      if (!play) {
        play = true;
        ballposX = 120;
        ballposY = 350;
        ballXdir = -1;
        ballYdir = -2;
        playerX = 310;
        score = 0;
        totalBricks = 21;

        repaint();
      }
    }
  }

  public void keyReleased(KeyEvent e) {}

  public void keyTyped(KeyEvent e) {}

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
    if (play) {
      if (
        new Rectangle(ballposX, ballposY, 20, 20)
          .intersects(new Rectangle(playerX, 550, 30, 8))
      ) {
        ballYdir = -ballYdir;
      }

      ballposX += ballXdir;
      ballposY += ballYdir;
      if (ballposX < 0) {
        ballXdir = -ballXdir;
      }
      if (ballposY < 0) {
        ballYdir = -ballYdir;
      }
      if (ballposX > 670) {
        ballXdir = -ballXdir;
      }
    }
    repaint();
  }
}
