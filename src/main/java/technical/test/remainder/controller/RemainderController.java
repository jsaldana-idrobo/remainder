package technical.test.remainder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import technical.test.remainder.model.TestCase;
import technical.test.remainder.service.RemainderService;
import technical.test.remainder.util.ResponseSerializer;

@RestController
@RequestMapping()
public class RemainderController {

    private final RemainderService remainderService;

    @Autowired
    public RemainderController(RemainderService remainderService) {
        this.remainderService = remainderService;
    }

    @GetMapping()
    public String hello () {
        return "Hello to Remainder technical test app";
    }

    @GetMapping(value = "/remainder", produces = {"application/json", "application/xml"})
    public String getRemainders(@RequestParam int x, @RequestParam int y, @RequestParam int n,
                                @RequestHeader(value = "Accept") String acceptHeader) {
        Integer result = remainderService.calculateRemainder(x, y, n);
        return ResponseSerializer.serializeResult(result, acceptHeader);
    }

    @PostMapping(value = "/remainder", produces = {"application/json", "application/xml"})
    public String postRemainders(@RequestBody TestCase testCase,
                                 @RequestHeader(value = "Accept") String acceptHeader) {
        Integer result = remainderService.calculateRemainder(
                testCase.getX(), testCase.getY(), testCase.getN());
        return ResponseSerializer.serializeResult(result, acceptHeader);
    }
}
