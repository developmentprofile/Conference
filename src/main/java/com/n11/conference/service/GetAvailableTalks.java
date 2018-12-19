package com.n11.conference.service;

import java.util.ArrayList;
import java.util.List;

import com.n11.conference.service.model.TalkDTO;

public class GetAvailableTalks {

	private TalkDTO[] items;
	private int capacity;

	public GetAvailableTalks(TalkDTO[] items, int capacity) {
		this.items = items;
		this.capacity = capacity;
	}

	public List<TalkDTO> solve() {
		int NB_ITEMS = items.length;
		// we use a matrix to store the max value at each n-th item
		int[][] matrix = new int[NB_ITEMS + 1][capacity + 1];

		// first line is initialized to 0
		for (int i = 0; i <= capacity; i++)
			matrix[0][i] = 0;

		// we iterate on items
		for (int i = 1; i <= NB_ITEMS; i++) {
			// we iterate on each capacity
			for (int j = 0; j <= capacity; j++) {
				if (items[i - 1].getTalkTime() > j)
					matrix[i][j] = matrix[i - 1][j];
				else
					// we maximize value at this rank in the matrix
					matrix[i][j] = Math.max(matrix[i - 1][j],
							matrix[i - 1][j - items[i - 1].getTalkTime()] + items[i - 1].getTalkTime());
			}
		}

		int res = matrix[NB_ITEMS][capacity];
		int w = capacity;
		List<TalkDTO> itemsSolution = new ArrayList<>();

		for (int i = NB_ITEMS; i > 0 && res > 0; i--) {
			if (res != matrix[i - 1][w]) {
				itemsSolution.add(items[i - 1]);
				// we remove items value and weight
				res -= items[i - 1].getTalkTime();
				w -= items[i - 1].getTalkTime();
			}
		}
		return itemsSolution;
//		return new PlannedTalkList(itemsSolution, matrix[NB_ITEMS][capacity]);
	}
}