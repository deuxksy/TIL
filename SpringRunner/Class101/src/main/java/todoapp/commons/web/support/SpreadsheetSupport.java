package todoapp.commons.web.support;

import todoapp.commons.domain.Spreadsheet;
import todoapp.commons.util.StreamUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * {@link Spreadsheet} 보조 ?��?��?��
 *
 * @author springrunner.kr@gmail.com
 */
public interface SpreadsheetSupport {

  /**
   * 주어�? 모델(model)?��?��?�� {@link Spreadsheet}�? 찾아 반환?��?��?��.
   *
   * @param model ?��?���? MVC 모델
   * @return
   * @throws IllegalArgumentException 값이 ?��거나, ?���? ?��?�� 발견?���? 발생
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
   * 주어�? ?? 목록?�� 값을 ?��결해?�� ?��?��?�� 문자?���? 만듭?��?��.
   * 문자?�� ?��결시 구분 문자�? ?��?��?��?��?��.
   *
   * @param cells     반복?��
   * @param delimiter 구분 문자
   * @return
   */
  default String joining(Iterable<Spreadsheet.Cell<?>> cells, CharSequence delimiter) {
    return StreamUtils.createStreamFromIterator(cells.iterator())
            .map(Spreadsheet.Cell::getValue)
            .map(String::valueOf)
            .collect(Collectors.joining(delimiter));
  }

}
