package teamMurange.Murange.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Entity;

@Embeddable
@Getter
@NoArgsConstructor
public class Figure {

    private float happiness;

    private float sad;

    private float angry;

    private float neutral;

    private float surprised;

    private float disgust;

    private float scared;

    // 평균내서 업데이트할 예정, 일단은 변경하는 로직으로 구현(테스트용)
    public void updateFigure(String updateEmotion, float updateFigure) {
        
        switch (updateEmotion) {
            case "happiness":
                this.happiness = updateFigure;
            case "sad":
                this.sad = updateFigure;
            case "angry":
                this.angry = updateFigure;
            case "neutral":
                this.neutral = updateFigure;
            case "disgust":
                this.disgust = updateFigure;
            case "surprised":
                this.surprised = updateFigure;
            case "scared":
                this.scared = updateFigure;
        }
    }


    @Builder
    public Figure (float happiness, float sad, float angry,float neutral, float surprised, float disgust, float scared) {
        this.happiness = happiness;
        this.sad = sad;
        this.angry = angry;
        this.neutral = neutral;
        this.surprised = surprised;
        this.disgust = disgust;
        this.scared = scared;
    }

}
