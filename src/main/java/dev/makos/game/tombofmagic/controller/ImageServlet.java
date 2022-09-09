package dev.makos.game.tombofmagic.controller;

import dev.makos.game.tombofmagic.service.ImageService;
import lombok.SneakyThrows;

import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@WebServlet("/images/*")
public class ImageServlet extends HttpServlet {
    ImageService imageService = ImageService.INSTANCE;

    @Override
    @SneakyThrows
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String nameImage = req.getRequestURI().replace("/images/", "");
        Optional<Path> file = imageService.getImagePath(nameImage);
        if (file.isPresent()) {
            try (ServletOutputStream outputStream = resp.getOutputStream()) {
                Files.copy(file.get(), outputStream);
            }
        }
    }
}
