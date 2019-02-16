package todoapp.commons.web.support;

import todoapp.commons.domain.Spreadsheet;
import todoapp.commons.util.StreamUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * {@link Spreadsheet} ë³´ì¡° ?´?˜?Š¤
 *
 * @author springrunner.kr@gmail.com
 */
public interface SpreadsheetSupport {

    /**
     * ì£¼ì–´ì§? ëª¨ë¸(model)?‚´?—?„œ {@link Spreadsheet}ë¥? ì°¾ì•„ ë°˜í™˜?•©?‹ˆ?‹¤.
     *
     * @param model ?Š¤?”„ë§? MVC ëª¨ë¸
     * @return
     * @throws IllegalArgumentException ê°’ì´ ?—†ê±°ë‚˜, ?‘ê°? ?´?ƒ ë°œê²¬?˜ë©? ë°œìƒ
     */
    default Spreadsheet obtainSpreadsheet(Map<String, Object> model) {
        List<Spreadsheet> spreadsheets = model.values()
                                              .stream()
                                              .filter(it -> it instanceof Spreadsheet)
                                              .map(it -> (Spreadsheet) it)
                                              .collect(Collectors.toList());
        if (spreadsheets.isEmpty()) {
            throw new IllegalArgumentException("spreadsheet object inside the model is required");
        }
        if (spreadsheets.size() > 1) {
            throw new IllegalArgumentException("multiple spreadsheet objects were found");
        }
        return spreadsheets.get(0);
    }

    /**
     * ì£¼ì–´ì§? ?? ëª©ë¡?— ê°’ì„ ?—°ê²°í•´?„œ ?•˜?‚˜?˜ ë¬¸ì?—´ë¡? ë§Œë“­?‹ˆ?‹¤.
     * ë¬¸ì?—´ ?—°ê²°ì‹œ êµ¬ë¶„ ë¬¸ìë¥? ?‚½?…?•©?‹ˆ?‹¤.
     *
     * @param cells     ë°˜ë³µ?
     * @param delimiter êµ¬ë¶„ ë¬¸ì
     * @return
     */
    default String joining(Iterable<Spreadsheet.Cell<?>> cells, CharSequence delimiter) {
        return StreamUtils.createStreamFromIterator(cells.iterator())
                          .map(Spreadsheet.Cell::getValue)
                          .map(String::valueOf)
                          .collect(Collectors.joining(delimiter));
    }

}
