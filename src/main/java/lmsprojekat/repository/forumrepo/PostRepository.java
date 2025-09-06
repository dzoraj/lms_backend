package lmsprojekat.repository.forumrepo;

import lmsprojekat.model.forum.Post;
import lmsprojekat.repository.SoftDeleteRepository;

public interface PostRepository extends SoftDeleteRepository<Post, Long> {}
