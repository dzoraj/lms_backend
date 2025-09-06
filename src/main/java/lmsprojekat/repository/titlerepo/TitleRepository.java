package lmsprojekat.repository.titlerepo;

import lmsprojekat.model.title.Title;
import lmsprojekat.repository.SoftDeleteRepository;

public interface TitleRepository extends SoftDeleteRepository<Title, Long> {}
