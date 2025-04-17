package albin2501.endpoint;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import albin2501.dto.ImageDto;
import albin2501.dto.TextArtDto;
import albin2501.exception.NotFoundException;
import albin2501.exception.ServiceException;
import albin2501.exception.ValidationException;
import albin2501.service.TextArtService;
import albin2501.util.Config;

import org.springframework.http.HttpStatus;

@RestController
@RequestMapping(path = TextArtEndpoint.url)
@CrossOrigin(origins = Config.frontendBase) // CORS access control
public class TextArtEndpoint {
    final static String url = "/textArt";

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public TextArtDto[] getTextArt() {
        try {
            return TextArtService.getTextArt();
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TextArtDto postTextArt(
                @RequestParam("image") MultipartFile image,
                @RequestParam("width") Long width,
                @RequestParam("height") Long height
            ) {
        try {
            return TextArtService.postTextArt(new ImageDto(image, width, height));
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        } catch (ValidationException e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage(), e);
        }
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public boolean deleteTextArt() {
        try {
            return TextArtService.deleteTextArt();
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public boolean deleteTextArtById(@PathVariable("id") Long id) {
        try {
            return TextArtService.deleteTextArtById(id);
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }
}
