package lmsprojekat.repository.forumrepo;

import lmsprojekat.model.forum.Topic;
import lmsprojekat.repository.SoftDeleteRepository;

public interface TopicRepository extends SoftDeleteRepository<Topic, Long> {}
