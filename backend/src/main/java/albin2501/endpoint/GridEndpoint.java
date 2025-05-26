package albin2501.endpoint;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import albin2501.dto.grid.*;
import albin2501.exception.*;
import albin2501.mapper.GridMapper;
import albin2501.service.GridService;
import albin2501.Application;

@RestController
@RequestMapping(path = "/grid")
@CrossOrigin(origins = Application.frontendBase)
public class GridEndpoint {

    @Autowired
    private GridService gridService;

    @GetMapping(value = "/data")
    @ResponseStatus(HttpStatus.OK)
    public GridDataDto getGridData() {
        try {
            return gridService.getGridData();
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GridDto getGrid(@PathVariable("id") Long id) {
        try {
            return GridMapper.INSTANCE.gridToGridDto(gridService.getGrid(id));
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @PatchMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GridDto putGrid(@PathVariable("id") Long id, @RequestBody GridSelectionDto gridSelectionDto) {
        try {
            return GridMapper.INSTANCE.gridToGridDto(gridService.putGrid(id, gridSelectionDto.pos1(), gridSelectionDto.pos2()));
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
            return GridMapper.INSTANCE.gridToGridDto(gridService.clearGrid(id));
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (ValidationException e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage(), e);
        }
    }
}
