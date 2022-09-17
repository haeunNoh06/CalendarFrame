package day3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;

public class CalendarFrame extends JFrame {

	public CalendarFrame() {					//생성자 : new를 통해 호출할 수 있다. 만들어진 클래스 자기 자신을 리턴함
		setSize(500,500);									//사이즈 설정
		setLocationRelativeTo(null);						//화면 중간
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);			//프로그램 정상 종료
		
		//﻿JFrame의 기본 레이아웃 : BorderLayout : 지정하지 않아도 BorderLayout이 됨
		//GridLayout : 2차원 맵 x, y ,좌표를 지도처럼 배치하는 레이아웃
		setLayout(new GridLayout(7,7));				//7x7 그리드 레이아웃 생성
		
		var weekdays = new String[] {					//weekdays의 String배열에 요일 값을 저장한다.
				"일", "월", "화", "수", "목", "금", "토"
		};
		
		Color[] colorList = {
				Color.RED,
				Color.BLACK, 
				Color.BLACK, 
				Color.BLACK, 
				Color.BLACK, 
				Color.BLACK, 
				Color.BLUE 
		};
		
		for ( int i =0; i < weekdays.length; i++ ) {
			var lb = new JLabel(weekdays[i]);
			
			lb.setForeground(colorList[i]);
			
			add(lb);
		}
		
		LocalDate now = LocalDate.now();												//현재 날짜
		LocalDate startDate = LocalDate.of(now.getYear(), now.getMonth(), 1);					//시작 날짜
		LocalDate firstDate = startDate.minusDays((startDate.getDayOfWeek().ordinal()+1 )%7);	//첫 번째 날짜
		
		for ( int i = 0; i < 42; i++) {								//JButton에 날짜 입력
			
			if ( firstDate.getMonth()==now.getMonth()) {
				var button = new JButton("" + firstDate.getDayOfMonth());
				
				if ( firstDate.compareTo(now) < 0 ) {			//
					button.setEnabled(false);
				}
				button.addActionListener(new ActionListener() {		//이 버튼에 액션리스너를 추가
					
					@Override
					public void actionPerformed(ActionEvent e) {
						JOptionPane.showMessageDialog(null, "클릭!");
					}
				});
				
				button.setForeground(colorList[i%7]);
				
				add(button);									
			}else {
				add(new JPanel());
			}
			
			firstDate = firstDate.plusDays(1);
		}
		
		//add(new JButton("A"), BorderLayout.NORTH);
		//add(new JButton("B"), BorderLayout.SOUTH);
	}
	
	public static void main(String[] args) {
		
		var frame = new CalendarFrame();			//CalendarFrame 생성자 실행
		//CalendarFrame frame = new CalendarFrame();
		
		frame.setVisible(true);
	}

}
