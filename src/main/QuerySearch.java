package main;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import facade.SeachFacade;
import querylogs.LogParser;

public class QuerySearch extends JPanel {

  private JTextField[] fields;
private JLabel suggestions = new JLabel("<html>1<br>2</html>");

  // Create a form with the specified labels, tooltips, and sizes.
  public QuerySearch(String[] labels, char[] mnemonics, int[] widths, String[] tips) {
    super(new BorderLayout());
    JPanel labelPanel = new JPanel(new GridLayout(labels.length, 1));
    JPanel fieldPanel = new JPanel(new GridLayout(labels.length, 1));
    add(labelPanel, BorderLayout.WEST);
    add(fieldPanel, BorderLayout.CENTER);
    fields = new JTextField[labels.length];

    for (int i = 0; i < labels.length; i += 1) {
      fields[i] = new JTextField();
      if (i < tips.length)
        fields[i].setToolTipText(tips[i]);
      if (i < widths.length)
        fields[i].setColumns(widths[i]);

      JLabel lab = new JLabel(labels[i], JLabel.RIGHT);
      lab.setLabelFor(fields[i]);
      if (i < mnemonics.length)
        lab.setDisplayedMnemonic(mnemonics[i]);

      labelPanel.add(lab);
      JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));
      p.add(fields[i]);
      fieldPanel.add(p);
    }
    fieldPanel.add(suggestions  );
    
  }

  public String getText(int i) {
    return (fields[i].getText());
  }

  public static void main(String[] args) {
	  
	  LogParser parser = new LogParser();
	  parser.gatherLogs("/data/TestFile.txt"); 
	  
	  
	  
    String[] labels = { "Query" };
    char[] mnemonics = { 'F', 'M', 'L', 'A' };
    int[] widths = { 15, 1, 15, 3 };
    String[] descs = { "Enter a Query" };

    final QuerySearch form = new QuerySearch(labels, mnemonics, widths, descs);

    JButton submit = new JButton("Submit Form");

    submit.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
    	  Collection<String> sugestedQueries = SeachFacade.getInstance().getSuggestions(form.getText(0));
        
        //sugestedQueries.add("Fish");
        form.showSuggestion(sugestedQueries);
      }
    });

    JFrame f = new JFrame("Text Form Example");
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.getContentPane().add(form, BorderLayout.NORTH);
    JPanel p = new JPanel();
    p.add(submit);
    f.getContentPane().add(p, BorderLayout.SOUTH);
    f.pack();
    f.setVisible(true);
  }

protected void showSuggestion(Collection<String> sugestedQueries) {
	StringBuilder sb = new StringBuilder();
	sb.append("<html>");
	for (String suggestion: sugestedQueries)
	{
		sb.append(suggestion).append("<br>");
	}
	sb.append("</html>");
	suggestions.setText(sb.toString());
}


}