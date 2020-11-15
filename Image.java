import java.io.*;

import javax.imageio.ImageIO;
import java.awt.image.*;
import java.awt.Color;

public class Image {

  int width;
  int height;
  int[] RGBArray;
  Color[] colors;

  BufferedImage buffered;

  public Image(String filename) {
    this.buffered = null;

    try {
      buffered = ImageIO.read(new File(filename));
      this.width = buffered.getWidth();
      this.height = buffered.getHeight();
      this.RGBArray = buffered.getRGB(0, 0, this.width, this.height, null, 0, width);
      colors = new Color[this.RGBArray.length];

      for (int i = 0; i < this.RGBArray.length; i++) {
        colors[i] = new Color(this.RGBArray[i]);
      }

    } catch (IOException e) {
      System.out.println("Erro ao ler a imagem: " + filename);
      System.exit(0);
    }
  }

  public void ToBmp(String output) {
    try {
      ImageIO.write(this.buffered, "bmp", new File(output));
      System.out.println("Feito!");
    } catch (IOException e) {
      System.out.println("Erro ao gerar arquivo de saida: " + output);
      System.exit(0);
    }
  }

  public Color acc(int i, int j) {
    if (i < 0 || i > this.height)
      return null;

    if (j < 0 || j > this.width)
      return null;

    int offset = (i * this.width) + j;
    return this.colors[offset];
  }

  public void setPixel(int y, int x, Color color) {
    if (y < 0 || y > this.height)
      return;

    if (x < 0 || x > this.width)
      return;

    int offset = (y * this.width) + x;
    this.colors[offset] = color;
    this.buffered.setRGB(x, y, color.getRGB());
  }
}
