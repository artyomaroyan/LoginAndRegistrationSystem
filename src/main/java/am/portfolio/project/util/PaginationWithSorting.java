package am.portfolio.project.util;

import lombok.*;

/**
 * @author Artyom Aroyan
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaginationWithSorting {

    private int pageSize;
    private int contentsNumber;
    private String sortDirection;
    private String sortElement;
}
