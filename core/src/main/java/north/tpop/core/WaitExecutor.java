package north.tpop.core;

import java.time.Duration;

public interface WaitExecutor {

    void apply(Element element, WaitPredicate predicate);

    void apply(Element element, WaitPredicate predicate, Duration timeout);
}
