import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.EmptyStackException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class MakeCalculator extends JFrame{
		private JTextArea displayArea;
		private JPanel buttonPanel;
		public MakeCalculator(String title) {
			super(title);
			
			setSize(500, 700);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setLayout(new GridLayout(2, 1, 3, 3));
			
			displayArea = new JTextArea();
			displayArea.setFont(displayArea.getFont().deriveFont(25f));
			this.displayArea.setEditable(false);
			add(this.displayArea);
			this.createButtonPane();
			add(this.buttonPanel);
			setVisible(true);
		}
		public void createButtonPane() {
			this.buttonPanel = new JPanel(new GridLayout(6,4));
			JButton a = new JButton("AC");
			a.setFont(a.getFont().deriveFont(25f));
			a.addActionListener(new ClearListener());
			
			JButton b = new JButton("+/-");
			b.setFont(b.getFont().deriveFont(25f));
			b.addActionListener(new posNegListener());
			
			JButton c = new JButton("%");
			c.setFont(c.getFont().deriveFont(25f));
			c.addActionListener(new operatorListener());
			
			JButton d = new JButton("/");
			d.setFont(d.getFont().deriveFont(25f));
			d.addActionListener(new operatorListener());
			
			JButton e = new JButton("(");
			e.setFont(e.getFont().deriveFont(25f));
			e.addActionListener(new operatorListener());
			
			JButton f = new JButton(")");
			f.setFont(f.getFont().deriveFont(25f));
			f.addActionListener(new operatorListener());
			
			JButton g = new JButton("\u221A");// Unicode for square root
			g.setFont(g.getFont().deriveFont(25f));
			g.addActionListener(new operatorListener());
			
			JButton h = new JButton("^");
			h.setFont(h.getFont().deriveFont(25f));
			h.addActionListener(new operatorListener());
			
			JButton i = new JButton("7");
			i.setFont(i.getFont().deriveFont(25f));
			i.addActionListener(new numberListener());
			
			JButton j = new JButton("8");
			j.setFont(j.getFont().deriveFont(25f));
			j.addActionListener(new numberListener());
			
			JButton k = new JButton("9");
			k.setFont(k.getFont().deriveFont(25f));
			k.addActionListener(new numberListener());
			
			JButton l = new JButton("*");
			l.setFont(l.getFont().deriveFont(25f));
			l.addActionListener(new operatorListener());
			
			JButton m = new JButton("4");
			m.setFont(m.getFont().deriveFont(25f));
			m.addActionListener(new numberListener());
			
			JButton n = new JButton("5");
			n.setFont(n.getFont().deriveFont(25f));
			n.addActionListener(new numberListener());
			
			JButton o = new JButton("6");
			o.setFont(o.getFont().deriveFont(25f));
			o.addActionListener(new numberListener());
			
			JButton p = new JButton("-");
			p.setFont(p.getFont().deriveFont(25f));
			p.addActionListener(new operatorListener());
			
			JButton q = new JButton("1");
			q.setFont(q.getFont().deriveFont(25f));
			q.addActionListener(new numberListener());
			
			JButton r = new JButton("2");
			r.setFont(r.getFont().deriveFont(25f));
			r.addActionListener(new numberListener());
			
			JButton s = new JButton("3");
			s.setFont(s.getFont().deriveFont(25f));
			s.addActionListener(new numberListener());
			
			JButton t = new JButton("+");
			t.setFont(t.getFont().deriveFont(25f));
			t.addActionListener(new operatorListener());
			
			JButton u = new JButton("0");
			u.setFont(u.getFont().deriveFont(25f));
			u.addActionListener(new numberListener());
			
			JButton v = new JButton(".");
			v.setFont(v.getFont().deriveFont(25f));
			v.addActionListener(new operatorListener());
			
			JButton w = new JButton("=");
			w.addActionListener(new ResultListener());
			w.setFont(w.getFont().deriveFont(25f));

			
			
			this.buttonPanel.add(a);
			this.buttonPanel.add(b);
			this.buttonPanel.add(c);
			this.buttonPanel.add(d);
			this.buttonPanel.add(e);
			this.buttonPanel.add(f);
			this.buttonPanel.add(g);
			
			this.buttonPanel.add(h);
			this.buttonPanel.add(i);
			this.buttonPanel.add(j);
			this.buttonPanel.add(k);
			this.buttonPanel.add(l);
			this.buttonPanel.add(m);
			this.buttonPanel.add(n);
			this.buttonPanel.add(o);
			this.buttonPanel.add(p);
			this.buttonPanel.add(q);
			this.buttonPanel.add(r);
			this.buttonPanel.add(s);
			this.buttonPanel.add(t);
			this.buttonPanel.add(u);
			this.buttonPanel.add(v);
			this.buttonPanel.add(new JButton(" "));
			this.buttonPanel.add(w);
						
		}
		
		class ClearListener implements ActionListener{
			public void actionPerformed(ActionEvent clear) {
				displayArea.setText(null);
			}
		}
		
		
		
		class posNegListener implements ActionListener{
			public void actionPerformed(ActionEvent pN) {
				displayArea.append("(1-2)*");
			}
		}
			
		class numberListener implements ActionListener{
			public void actionPerformed(ActionEvent num) {
				JButton button = (JButton)num.getSource();
				String text = button.getText();
				displayArea.append(text);
			}
		}
		class operatorListener implements ActionListener{
			public void actionPerformed(ActionEvent op) {
				JButton opButton = (JButton)op.getSource();
				String opText = opButton.getText();
				displayArea.append(opText);
				
			}
		}
		class ResultListener implements ActionListener {
			public void actionPerformed(ActionEvent eq) {
				try {// Try catch block

			
				
				String text = displayArea.getText();

				evaluateExpression program = new evaluateExpression();
				ArrayList<String> postfixList = program.infix2Postfix(text);
					double answer = program.evaluatePostfix(postfixList);
					displayArea.append(" = " + answer);
				
				if(text.contains("%")) {
					String[] s = text.split("\\%");
					double num1 = Double.parseDouble(s[0]);
					double answer1 = num1 / 100;
					displayArea.append(" = " + answer1);
				}
				}
				catch(EmptyStackException obj) {
					displayArea.append("Improper way of operations");// Error handling
				
				}
		
			
			}
		}
}

		

