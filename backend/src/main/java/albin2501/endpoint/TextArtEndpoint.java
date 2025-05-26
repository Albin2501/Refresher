package albin2501.endpoint;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import java.util.Arrays;
import albin2501.dto.textArt.*;
import albin2501.exception.*;
import albin2501.mapper.TextArtMapper;
import albin2501.service.TextArtService;
import albin2501.Application;

@RestController
@RequestMapping(path = "/textArt")
@CrossOrigin(origins = Application.frontendBase)
public class TextArtEndpoint {

    @Autowired
    private TextArtService textArtService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public TextArtDto[] getTextArt() {
        try {
            return Arrays.stream(textArtService.getTextArt()).
                    map(x -> TextArtMapper.INSTANCE.textArtToTextArtDto(x)).toArray(TextArtDto[]::new);
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
            return TextArtMapper.INSTANCE.textArtToTextArtDto(textArtService.postTextArt(image, width, height));
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        } catch (ValidationException e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage(), e);
        }
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteTextArt() {
        try {
            textArtService.deleteTextArt();
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTextArtById(@PathVariable("id") Long id) {
        try {
            textArtService.deleteTextArtById(id);
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }
}
