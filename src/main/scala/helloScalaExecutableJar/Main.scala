package helloScalaExecutableJar

import javax.swing.JFrame

object Main {

  def main(args: Array[String]) {
    val frame = new JFrame("Scala Executable Jar")
    frame.setBounds(300, 200, 250, 100)
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true)
  }
}