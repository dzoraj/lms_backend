package lmsprojekat.dto.teachingdto.schedule;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

public class RecurringTeachingSessionRequest {
	private Long courseRealizationId;
	private Long teachingTypeId;
	private LocalDate startDate; // semester start
	private LocalDate endDate; // end	
	private LocalTime startTime; // class starta
	private LocalTime endTime; // end
	private Set<DayOfWeek> daysOfWeek;
	private List<Long> learningOutcomeIds;
	private List<LocalDate> skipDates; // holidays/ exceptions

	public RecurringTeachingSessionRequest() {
	}

	public RecurringTeachingSessionRequest(Long courseRealizationId, Long teachingTypeId, LocalDate startDate,
			LocalDate endDate, LocalTime startTime, LocalTime endTime, Set<DayOfWeek> daysOfWeek,
			List<Long> learningOutcomeIds, List<LocalDate> skipDates) {
		super();
		this.courseRealizationId = courseRealizationId;
		this.teachingTypeId = teachingTypeId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.daysOfWeek = daysOfWeek;
		this.learningOutcomeIds = learningOutcomeIds;
		this.skipDates = skipDates;
	}

	public Long getCourseRealizationId() {
		return courseRealizationId;
	}

	public void setCourseRealizationId(Long courseRealizationId) {
		this.courseRealizationId = courseRealizationId;
	}

	public Long getTeachingTypeId() {
		return teachingTypeId;
	}

	public void setTeachingTypeId(Long teachingTypeId) {
		this.teachingTypeId = teachingTypeId;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	public Set<DayOfWeek> getDaysOfWeek() {
		return daysOfWeek;
	}

	public void setDaysOfWeek(Set<DayOfWeek> daysOfWeek) {
		this.daysOfWeek = daysOfWeek;
	}

	public List<Long> getLearningOutcomeIds() {
		return learningOutcomeIds;
	}

	public void setLearningOutcomeIds(List<Long> learningOutcomeIds) {
		this.learningOutcomeIds = learningOutcomeIds;
	}

	public List<LocalDate> getSkipDates() {
		return skipDates;
	}

	public void setSkipDates(List<LocalDate> skipDates) {
		this.skipDates = skipDates;
	}

}
