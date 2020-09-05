package machura.przemyslaw.informationmanagerdomain.domain.tags.extraction.fromtext;

import io.vavr.control.Either;
import machura.przemyslaw.informationmanagerdomain.domain.errors.Fault;
import machura.przemyslaw.informationmanagerdomain.domain.tags.Tag;

import java.io.File;
import java.util.List;

public interface TagsExtractor {
    List<Either<Fault, Tag>> extract(String text);

    List<Either<Fault, Tag>> extract(File file);
}
