public class Main {
    public static void main(String[] args) {
        try {
            String inputText = "hello huffman";
            HuffmanCoder coder = new HuffmanCoder(inputText);

            String encoded = coder.encode(inputText);
            System.out.println("Encoded: " + encoded);

            String decoded = coder.decode(encoded);
            System.out.println("Decoded: " + decoded);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
