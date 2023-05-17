package domain.comparator;

import java.util.Comparator;

import domain.Training;

public class TrainingComparatorByMark implements Comparator<Training> {

    @Override
    public int compare(Training t1, Training t2) {

	Double t1AverageMark = t1.getAverageMark();

	Double t2AverageMark = t2.getAverageMark();

	return t1AverageMark.compareTo(t2AverageMark);
    }

}
