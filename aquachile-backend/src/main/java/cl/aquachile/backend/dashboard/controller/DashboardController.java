package cl.aquachile.backend.dashboard.controller;

import cl.aquachile.backend.dashboard.service.DashboardService;import org.springframework.web.bind.annotation.*;import java.util.*;
@RestController @RequestMapping("/api/dashboard") public class DashboardController {private final DashboardService service;public DashboardController(DashboardService service){this.service=service;}@GetMapping("/resumen")public Map<String,Integer> resumen(){return service.resumen();}}
