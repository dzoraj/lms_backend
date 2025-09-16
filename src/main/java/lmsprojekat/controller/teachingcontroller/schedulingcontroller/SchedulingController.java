package lmsprojekat.controller.teachingcontroller.schedulingcontroller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lmsprojekat.dto.teachingdto.schedule.ConflictProbeDTO;
import lmsprojekat.dto.teachingdto.schedule.ConflictResultDTO;
import lmsprojekat.dto.teachingdto.schedule.RecurringTeachingSessionRequest;
import lmsprojekat.dto.teachingdto.schedule.UnifiedScheduleItemDTO;
import lmsprojekat.service.teachingservice.scheduleservice.SchedulingService;

@RestController
@RequestMapping("/api/schedule")
public class SchedulingController {

    private final SchedulingService service;

    public SchedulingController(SchedulingService service) {
        this.service = service;
    }

    @GetMapping("/studyYear/{studyYearId}")
    public List<UnifiedScheduleItemDTO> unified(
            @PathVariable Long studyYearId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to) {
        return service.unifiedByStudyYear(studyYearId, from, to);
    }

    @GetMapping("/studyYear/{studyYearId}/day/{date}")
    public List<UnifiedScheduleItemDTO> day(
            @PathVariable Long studyYearId,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        var from = date.atStartOfDay();
        var to = date.atTime(LocalTime.MAX);
        return service.unifiedByStudyYear(studyYearId, from, to);
    }

    @PostMapping("/teaching/recurring")
    public List<Long> createRecurringTeaching(@RequestBody RecurringTeachingSessionRequest req) {
        return service.createRecurringTeachingSessions(req);
    }

    @PostMapping("/conflicts")
    public ConflictResultDTO conflicts(@RequestBody List<ConflictProbeDTO> probes) {
        return service.checkConflicts(probes);
    }
}
