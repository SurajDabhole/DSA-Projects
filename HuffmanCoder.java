import java.util.HashMap;

public class HuffmanCoder {
    private HashMap<Character, String> encoder;
    private HashMap<String, Character> decoder;

    public HuffmanCoder(String text) throws Exception {
        // Frequency Map
        HashMap<Character, Integer> freqMap = new HashMap<>();
        for (char ch : text.toCharArray()) {
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
        }

        // Build Heap
        Heap<Node> heap = new Heap<>();
        for (var entry : freqMap.entrySet()) {
            heap.insert(new Node(entry.getKey(), entry.getValue()));
        }

        // Build Huffman Tree
        while (heap.size() > 1) {
            Node left = heap.remove();
            Node right = heap.remove();
            Node parent = new Node('\0', left.cost + right.cost);
            parent.left = left;
            parent.right = right;
            heap.insert(parent);
        }

        // Generate Encodings
        Node root = heap.remove();
        encoder = new HashMap<>();
        decoder = new HashMap<>();
        buildCodeTable(root, "");
    }

    private void buildCodeTable(Node node, String code) {
        if (node == null) return;
        if (node.left == null && node.right == null) {
            encoder.put(node.data, code);
            decoder.put(code, node.data);
        }
        buildCodeTable(node.left, code + "0");
        buildCodeTable(node.right, code + "1");
    }

    public String encode(String input) {
        StringBuilder encoded = new StringBuilder();
        for (char ch : input.toCharArray()) {
            encoded.append(encoder.get(ch));
        }
        return encoded.toString();
    }

    public String decode(String encoded) {
        StringBuilder decoded = new StringBuilder();
        StringBuilder tempCode = new StringBuilder();
        for (char ch : encoded.toCharArray()) {
            tempCode.append(ch);
            if (decoder.containsKey(tempCode.toString())) {
                decoded.append(decoder.get(tempCode.toString()));
                tempCode.setLength(0);  // reset the temporary code
            }
        }
        return decoded.toString();
    }
}
