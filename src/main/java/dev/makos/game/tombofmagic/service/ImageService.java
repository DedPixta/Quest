package dev.makos.game.tombofmagic.service;

import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Optional;

public enum ImageService {
    INSTANCE;

    private final Path root = Path.of(
            Objects.requireNonNull(ImageService.class.getResource("/"))
                    .toString()
                    .replace("file:/", "")
                    .concat("../images/")
    );

    @SneakyThrows
    ImageService() {
        Files.createDirectories(root);
    }

    @SneakyThrows
    public Optional<Path> getImagePath(String filename) {
        return Optional.of(root.resolve(filename));
    }
}
