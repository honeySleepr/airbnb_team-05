//
//  InformationCell.swift
//  Airbnb
//
//  Created by Jason on 2022/05/31.
//

import UIKit

final class InformationCell: UICollectionViewCell {
    
    static let identifier = "InformationCell"
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        appendSubView()
        informationLayout()
    }
    
    @available(*, unavailable)
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        fatalError()
    }
    
    private let starButton: UIButton = {
        let star = UIButton()
        star.setImage(systemName: "star.fill")
        return star
    }()
    
    private let scoreLabel: UILabel = {
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
    
    private let reviewLabel: UILabel = {
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
    
    private let lodgingLabel: UILabel = {
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
    
    private let priceLabel: UILabel = {
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
    
    func configure(score: String?, review: String?, lodging: String?, price: String?) {
        scoreLabel.text = score
        reviewLabel.text = review
        lodgingLabel.text = lodging
        priceLabel.text = price
    }
    
    private func appendSubView() {
        contentView.addSubview(starButton)
        contentView.addSubview(scoreLabel)
        contentView.addSubview(reviewLabel)
        contentView.addSubview(lodgingLabel)
        contentView.addSubview(priceLabel)
    }
    
    private func informationLayout() {
        
        starButton.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            starButton.topAnchor.constraint(equalTo: topAnchor),
            starButton.leadingAnchor.constraint(equalTo: contentView.leadingAnchor),
            starButton.trailingAnchor.constraint(equalTo: contentView.leadingAnchor),
            starButton.bottomAnchor.constraint(equalTo: bottomAnchor)
        ])
        
        scoreLabel.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            scoreLabel.topAnchor.constraint(equalTo: starButton.topAnchor),
            scoreLabel.leadingAnchor.constraint(equalTo: starButton.trailingAnchor, constant: 5),
            scoreLabel.trailingAnchor.constraint(equalTo: starButton.leadingAnchor),
        ])
        
        reviewLabel.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            reviewLabel.topAnchor.constraint(equalTo: scoreLabel.topAnchor),
            reviewLabel.leadingAnchor.constraint(equalTo: scoreLabel.leadingAnchor),
            reviewLabel.trailingAnchor.constraint(equalTo: scoreLabel.trailingAnchor),
            reviewLabel.heightAnchor.constraint(equalTo: scoreLabel.heightAnchor)
        ])
        
        lodgingLabel.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            lodgingLabel.topAnchor.constraint(equalTo: scoreLabel.topAnchor, constant: 9),
            lodgingLabel.leadingAnchor.constraint(equalTo: contentView.leadingAnchor),
            lodgingLabel.trailingAnchor.constraint(equalTo: contentView.trailingAnchor),
        ])
        
        priceLabel.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            priceLabel.topAnchor.constraint(equalTo: lodgingLabel.topAnchor, constant: 8),
            priceLabel.leadingAnchor.constraint(equalTo: contentView.leadingAnchor),
            priceLabel.trailingAnchor.constraint(equalTo: contentView.trailingAnchor),
            priceLabel.bottomAnchor.constraint(equalTo: bottomAnchor)
        ])
    }
}
