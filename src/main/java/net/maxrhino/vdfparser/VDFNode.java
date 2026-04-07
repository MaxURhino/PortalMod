package net.maxrhino.vdfparser;

import java.util.*;

public class VDFNode {
    public String key;
    public String value;
    public Map<String, List<VDFNode>> children = new LinkedHashMap<>();

    public VDFNode(String key) {
        this.key = key;
    }

    public boolean isLeaf() {
        return value != null;
    }

    public String get(String path) {
        String[] parts = path.split("\\.");
        VDFNode current = this;

        for (String part : parts) {
            List<VDFNode> list = current.children.get(part);
            if (list == null || list.isEmpty()) return null;
            current = list.get(0);
        }

        return current.value;
    }
}
