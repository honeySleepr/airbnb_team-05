//
//  InformationCell.swift
//  Airbnb
//
//  Created by Jason on 2022/05/31.
//

import UIKit

final class InformationCell: UICollectionViewCell {
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        
    }
    
    @available(*, unavailable)
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        fatalError()
    }
    
    private let star: UIButton = {
        let star = UIButton()
        star.setImage(systemName: "star.fill")
        return star
    }()
    
    private let score: UILabel = {
        let score = UILabel()
        score.numberOfLines = 0
        let scoreAttributedString = NSMutableAttributedString(string: "(후기 50개)")
        let length = scoreAttributedString.length
        scoreAttributedString.addAttributes([.foregroundColor:UIColor.black,
                                             .font: UIFont.systemFont(ofSize: 12, weight: .bold)],
                                            range: NSRange(location: 0, length: length))
        score.attributedText = scoreAttributedString
        return score
    }()
    
    private let review: UILabel = {
        let review = UILabel()
        review.numberOfLines = 0
        let reviewAttributedString = NSMutableAttributedString(string: "4.98")
        let length = reviewAttributedString.length
        reviewAttributedString.addAttributes([.foregroundColor: UIColor.black,
                                        .font: UIFont.systemFont(ofSize: 12, weight: .bold)],
                                       range: NSRange(location: 0, length: length))
        review.attributedText = reviewAttributedString
        return review
    }()
    
    private let lodging: UILabel = {
        let lodging = UILabel()
        lodging.numberOfLines = 0
        let lodgingAttributedString = NSMutableAttributedString(string: "비담집, 비우고담은집(집전체, 최대4인)")
        let length = lodgingAttributedString.length
        lodgingAttributedString.addAttributes([.foregroundColor: UIColor.black,
                                               .font: UIFont.systemFont(ofSize: 12, weight: .bold)],
                                              range: NSRange(location: 0, length: length))
        lodging.attributedText = lodgingAttributedString
        return lodging
    }()
    
    private let price: UILabel = {
       let price = UILabel()
        price.numberOfLines = 0
        let priceAttributedString = NSMutableAttributedString(string: "₩308,571 / 박")
        let length = priceAttributedString.length
        priceAttributedString.addAttributes([.foregroundColor: UIColor.black,
                                             .font: UIFont.systemFont(ofSize: 12, weight: .bold)],
                                            range: NSRange(location: 0, length: length))
        price.attributedText = priceAttributedString
        return price
    }()
    
    
}
