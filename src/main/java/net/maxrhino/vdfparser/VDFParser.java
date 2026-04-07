package net.maxrhino.vdfparser;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class VDFParser {
    private final VDFTokenizer tokenizer;
    private String current;
    private final Path basePath;

    public VDFParser(String input, Path basePath) {
        this.tokenizer = new VDFTokenizer(input);
        this.basePath = basePath;
        this.current = tokenizer.nextToken();
    }

    private void next() {
        current = tokenizer.nextToken();
    }

    public VDFNode parse() {
        return parseNode();
    }

    private VDFNode parseNode() {
        String key = current;
        next();
        VDFNode node = new VDFNode(key);
        if ("#include".equals(key)) {
            String file = current;
            next();
            try {
                Path includePath = basePath.resolve(file);
                String content = Files.readString(includePath);
                VDFParser sub = new VDFParser(content, includePath.getParent());
                VDFNode included = sub.parse();
                node.children.putAll(included.children);
            } catch (IOException e) {
                throw new RuntimeException("Failed to include file: " + file, e);
            }
            return node;
        }
        if ("{".equals(current)) {
            next();
            while (current != null && !"}".equals(current)) {
                VDFNode child = parseNode();
                node.children
                        .computeIfAbsent(child.key, k -> new ArrayList<>())
                        .add(child);
            }
            next();
        } else {
            node.value = current;
            next();
        }
        return node;
    }
}