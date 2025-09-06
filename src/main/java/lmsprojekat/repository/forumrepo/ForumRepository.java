package lmsprojekat.repository.forumrepo;

import lmsprojekat.model.forum.Forum;
import lmsprojekat.repository.SoftDeleteRepository;

public interface ForumRepository extends SoftDeleteRepository<Forum, Long> {}
