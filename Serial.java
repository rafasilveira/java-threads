import java.awt.Color;

// import javax.swing.plaf.ColorUIResource;

public class Serial {

  public static void main(String[] args) {
  // public Serial(String[] args) {
    if (args.length < 3) {
      System.out.printf("Uso: <imagem_de_entrada.bmp> <saida.bmp> <tamanho_filtro>\n");
      return;
    }

    if (Integer.parseInt(args[2]) % 2 == 0) {
      System.out.println("Tamanho do filtro precisa ser ímpar");
      return;
    }

    if (Integer.parseInt(args[2]) > 15) {
      System.out.println("Tamanho do filtro precisa ser menor que 15");
      return;
    }

    int filterSize = Integer.parseInt(args[2]);

    Image input = new Image(args[0]);
    Image output = new Image(args[0]);

    System.out.println("Largura da imagem: " + input.width);
    System.out.println("Altura da imagem: " + input.height);

    // int[] filter_buffer_red = new int[15 * 15];
    // int[] filter_buffer_green = new int[15 * 15];
    // int[] filter_buffer_blue = new int[15 * 15];

    int[] filter_buffer_red = new int[filterSize * filterSize];
    int[] filter_buffer_green = new int[filterSize * filterSize];
    int[] filter_buffer_blue = new int[filterSize * filterSize];

    int filter_reach = filterSize / 2;

    int width = input.width;
    int height = input.height;

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int filter_offset = 0;

        for (int r = -filter_reach; r <= filter_reach; r++) {
          for (int c = -filter_reach; c <= filter_reach; c++) {
            int ii = i + r;
            int jj = j + c;

            if (ii > 0 && ii < height && jj > 0 && jj < width) {
              Color pixel = output.acc(ii, jj);
              filter_buffer_red[filter_offset] = pixel.getRed();
              filter_buffer_green[filter_offset] = pixel.getGreen();
              filter_buffer_blue[filter_offset] = pixel.getBlue();
              filter_offset++;
            }
          }
        }

        int mean_offset = filter_offset / 2;
        sort_buffer(filter_buffer_red, 0, filter_offset - 1);
        sort_buffer(filter_buffer_green, 0, filter_offset - 1);
        sort_buffer(filter_buffer_blue, 0, filter_offset - 1);

        int r = filter_buffer_red[mean_offset];
        int g = filter_buffer_green[mean_offset];
        int b = filter_buffer_blue[mean_offset];

        Color color = new Color(r, g, b);
        // Color color = new Color(0, 0, 0);
        output.setPixel(i, j, color);

      }
    }

    output.ToBmp(args[1]);

  }

  static void sort_buffer(int[] buffer, int low, int high) {
    if (low < high) {
      int i = partition(buffer, low, high);

      sort_buffer(buffer, low, i - 1);
      sort_buffer(buffer, i + 1, high);
    }
  }

  static int partition(int[] buffer, int low, int high) {
    int pivot = buffer[high];
    int i = low;

    for (int j = low; j < high; j++) {
      if (buffer[j] <= pivot) {
        int aux = buffer[i];
        buffer[i] = buffer[j];
        buffer[j] = aux;
        i++;
      }
    }

    int aux = buffer[i];
    buffer[i] = buffer[high];
    buffer[high] = aux;

    return i;
  }

  static void printArray(int[] arr) {
    for (int i = 0; i < arr.length; i++) {
      System.out.printf(arr[i] + ", ");
    }
    System.out.println();
  }

}