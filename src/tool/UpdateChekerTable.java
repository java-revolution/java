package tool;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class UpdateChekerTable extends JTable {
	UpdateChekerTable(DefaultTableModel model) {
		super(model);
	}

	public Class getColumnClass(int column) {
		TableColumn col = this.getColumnModel().getColumn(column);
		Object colId = col.getIdentifier();

		if ("チェック".equals(colId)) {
			// セルのタイプにJCheckBoxを設定
			return Boolean.class;
		}
		return super.getColumnClass(column);
	}
}
