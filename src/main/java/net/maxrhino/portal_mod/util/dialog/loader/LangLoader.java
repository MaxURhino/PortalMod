package net.maxrhino.portal_mod.util.dialog.loader;

import com.mojang.datafixers.util.Pair;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.maxrhino.portal_mod.PortalMod;
import net.maxrhino.vdfparser.VDFNode;
import net.maxrhino.vdfparser.VDFParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class LangLoader {
    private final String language;

    public LangLoader(String language) {
        this.language = language;
    }

    private Optional<VDFNode> getFile(LangType langType) {
        Scanner scanner = new Scanner(language);
        Optional<Path> resourcesPath;
        Optional<ModContainer> modContainer = FabricLoader.getInstance().getModContainer(PortalMod.MOD_ID);
        if (modContainer.isPresent()) {
            resourcesPath = modContainer.get().findPath("assets/portal_mod/resource");
            if (resourcesPath.isPresent()) {
                List<Pair<Path, VDFNode>> scanned = scanner.scanFolder(resourcesPath.get());
                if (!scanned.isEmpty()) {
                    for (Pair<Path, VDFNode> pair : scanned) {
                        Path path = pair.getFirst();
                        String name = path.getFileName().toString();
                        VDFNode node = pair.getSecond();

                        if (langType == LangType.CLOSED_CAPTION) {
                            if (name.startsWith("closecaption")) {
                                return Optional.of(node.children.get("Tokens").getFirst());
                            }
                        } else {
                            return Optional.of(node.children.get("Tokens").getFirst());
                        }
                    }
                }
            }
        }
        return Optional.empty();
    }

    public enum LangType {
        CLOSED_CAPTION,
        LANG;
    }

    public static class Scanner {
        private final String language;

        public Scanner(String language) {
            this.language = language;
        }

        private static List<Pair<Path, VDFNode>> paths;

        public List<Pair<Path, VDFNode>> scanFolder(Path folder) {
            paths = new ArrayList<>();
            try (Stream<Path> paths = Files.walk(folder)) {
                paths
                        .filter(Files::isRegularFile)
                        .forEach(path -> checkFile(path, this.language));
            } catch (IOException e) {
                PortalMod.LOGGER.error(e.getLocalizedMessage());
            }
            return paths;
        }

        private static void checkFile(Path file, String language) {
            try {
                String content = Files.readString(file);

                VDFParser parser = new VDFParser(content, file.getParent());
                VDFNode root = parser.parse();

                if (isFileWithLanguage(language, root)) {
                    paths.add(Pair.of(file, root));
                }
            } catch (IOException e) {
                PortalMod.LOGGER.error(e.getLocalizedMessage());
            }
        }

        private static boolean isFileWithLanguage(String language, VDFNode root) {
            if (!"lang".equals(root.key)) return false;

            String lang = root.get("Language");

            return language.equalsIgnoreCase(lang);
        }
    }
}
