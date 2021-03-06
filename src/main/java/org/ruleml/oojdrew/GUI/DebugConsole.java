// OO jDREW - An Object Oriented extension of the Java Deductive Reasoning Engine for the Web
// Copyright (C) 2005 Marcel Ball
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public
// License along with this library; if not, write to the Free Software
// Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA

package org.ruleml.oojdrew.GUI;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;

public class DebugConsole extends JFrame {

    private JScrollPane scrollPaneDebugText;
    private JTextPane textPane;
    
    private JPopupMenu contextMenu;
    
    /** Creates new form DebugConsole */
    public DebugConsole() {
        super();
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {
        setTitle("DebugConsole");
        setName("debugFrame");
        
        scrollPaneDebugText = new JScrollPane();
        textPane = new JTextPane();
        
        scrollPaneDebugText.setViewportView(textPane);
        scrollPaneDebugText.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPaneDebugText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        getContentPane().add(scrollPaneDebugText, java.awt.BorderLayout.CENTER);

        contextMenu = new JPopupMenu("Edit");
        contextMenu.add(new ClearAction(textPane));
       
        textPane.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                requestFocus();
                processMouseClick(e);
            }
        });
        
        pack();
        this.setBounds(1, 1, 600, 800);
    }

    public JTextPane getTextPane() {
        return textPane;
    }
    
    public void updateUI() {
        SwingUtilities.updateComponentTreeUI(getTextPane());
    }
    
    protected void processMouseClick(MouseEvent e) {
        // Only interested in the right button
        if (SwingUtilities.isRightMouseButton(e)) {
            // Display the menu
            Point pt = SwingUtilities.convertPoint(e.getComponent(),
                    e.getPoint(), this);
            contextMenu.show(this, pt.x, pt.y);
        }
    }
    
    class ClearAction extends AbstractAction {
        private final JTextPane textPane;

        public ClearAction(final JTextPane textPane) {
            super("Clear console");
            this.textPane = textPane;
        }

        public void actionPerformed(ActionEvent e) {
            textPane.setText("");
        }

        public boolean isEnabled() {
            if (textPane.isEnabled()) {
                return true;
            } else {
                return false;
            }
        }
    }
}
