package albin2501.endpoint;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import albin2501.dto.GridDataDto;
import albin2501.dto.GridDto;
import albin2501.dto.GridSelectionDto;
import albin2501.exception.NotFoundException;
import albin2501.exception.ServiceException;
import albin2501.exception.ValidationException;
import albin2501.service.GridService;
import albin2501.util.Config;

import org.springframework.http.HttpStatus;

@RestController
@RequestMapping(path = GridEndpoint.url)
@CrossOrigin(origins = Config.frontendBase) // CORS access control
public class GridEndpoint {
    final static String url = "/grid";

    @GetMapping(value = "/data")
    @ResponseStatus(HttpStatus.OK)
    public GridDataDto getGridData() {
        try {
            return GridService.getGridData();
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GridDto getGrid(@PathVariable("id") Long id) {
        try {
            return GridService.getGrid(id);
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GridDto putGrid(@PathVariable("id") Long id, @RequestBody GridSelectionDto gridSelectionDto) {
        try {
            gridSelectionDto.id = id;
            return GridService.putGrid(gridSelectionDto);
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (ValidationException e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage(), e);
        }
    }

    @PutMapping(value = "/clear/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GridDto clearGrid(@PathVariable("id") Long id) throws Exception {
        try {
            return GridService.clearGrid(id);
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (ValidationException e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage(), e);
        }
    }
}
