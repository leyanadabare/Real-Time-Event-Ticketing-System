package controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import services.ConfigurationService;
import util.Configuration;

@RestController
@RequestMapping("/api/configuration")
@CrossOrigin(origins = "*")
public class ConfigurationController {

    @Autowired
    private ConfigurationService configurationService;

    // Endpoint to get configuration
    @GetMapping
    public Configuration getConfiguration() {
        System.out.println("Fetching configuration...");
        return configurationService.getConfiguration();
    }

    // Endpoint to save configuration
    @PostMapping
    public String saveConfiguration(@RequestBody Configuration configuration) {
        System.out.println("Saving configuration: " + configuration);
        configurationService.saveConfiguration(configuration);
        return "Configuration saved successfully!";
    }
}
