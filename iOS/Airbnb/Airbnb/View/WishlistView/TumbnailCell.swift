//
//  TumbnailCell.swift
//  Airbnb
//
//  Created by Jason on 2022/05/31.
//

import UIKit

final class TumbnailCell: UICollectionViewCell {
    
    static let identifier = "TumbnailCell"
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        appendSubView()
        tumbnailLayout()
        informationLayout()
    }
    
    @available(*, unavailable)
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        fatalError()
        
    }
    
    private let tumbNailImage: UIImageView = {
        let tumbNailImage = UIImageView()
        return tumbNailImage
    }()
    
    private let superHost: UIButton = {
        let superHost = UIButton()
        superHost.setTitle("  슈퍼호스트  ", for: .normal)
        superHost.setTitleColor(UIColor.black, for: .normal)
        superHost.backgroundColor = .white
        superHost.titleLabel?.font = UIFont.systemFont(ofSize: 15, weight: .medium)
        superHost.layer.cornerRadius = 10
        return superHost
    }()
    
    private let heart: UIButton = {
        let heart = UIButton()
        heart.setImage(systemName: "heart.fill")
        heart.tintColor = .systemRed
        return heart
    }()
    
    private let starButton: UIButton = {
        let star = UIButton()
        star.setImage(systemName: "star.fill")
        star.tintColor = .systemRed
        return star
    }()
    
    private let scoreLabel: UILabel = {
        let score = UILabel()
        score.numberOfLines = 0
        let scoreAttributedString = NSMutableAttributedString(string: "(후기 50개)")
        let length = scoreAttributedString.length
        scoreAttributedString.addAttributes([.foregroundColor:UIColor.black,
                                             .font: UIFont.systemFont(ofSize: 12, weight: .light)],
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
                                        .font: UIFont.systemFont(ofSize: 12, weight: .light)],
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
                                               .font: UIFont.systemFont(ofSize: 20, weight: .bold)],
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
                                             .font: UIFont.systemFont(ofSize: 17, weight: .bold)],
                                            range: NSRange(location: 0, length: length))
        price.attributedText = priceAttributedString
        return price
    }()
    
    
    func configure(image: UIImage?, score: String?, review: String?, lodging: String?, price: String?) {
        tumbNailImage.image = image
        scoreLabel.text = score
        reviewLabel.text = review
        lodgingLabel.text = lodging
        priceLabel.text = price
    }
    
    private func appendSubView() {
        contentView.addSubview(tumbNailImage)
        contentView.addSubview(superHost)
        contentView.addSubview(heart)
        contentView.addSubview(starButton)
        contentView.addSubview(scoreLabel)
        contentView.addSubview(reviewLabel)
        contentView.addSubview(lodgingLabel)
        contentView.addSubview(priceLabel)
    }
    
    private func tumbnailLayout() {
        tumbNailImage.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            tumbNailImage.topAnchor.constraint(equalTo: contentView.topAnchor),
            tumbNailImage.leadingAnchor.constraint(equalTo: contentView.leadingAnchor),
            tumbNailImage.trailingAnchor.constraint(equalTo: contentView.trailingAnchor),
            tumbNailImage.heightAnchor.constraint(equalToConstant: 200)
        ])
        
        superHost.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            superHost.topAnchor.constraint(equalTo: contentView.topAnchor, constant: 16),
            superHost.leadingAnchor.constraint(equalTo: contentView.leadingAnchor, constant: 16)
        ])
        
        heart.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            heart.topAnchor.constraint(equalTo: contentView.topAnchor, constant: 19),
            heart.trailingAnchor.constraint(equalTo: contentView.trailingAnchor, constant: -17)
        ])
    }
    
    private func informationLayout() {
        starButton.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            starButton.topAnchor.constraint(equalTo: tumbNailImage.bottomAnchor, constant: 10),
            starButton.leadingAnchor.constraint(equalTo: tumbNailImage.leadingAnchor),
            starButton.bottomAnchor.constraint(equalTo: scoreLabel.bottomAnchor)
        ])
        
        scoreLabel.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            scoreLabel.topAnchor.constraint(equalTo: tumbNailImage.bottomAnchor, constant: 10),
            scoreLabel.leadingAnchor.constraint(equalTo: starButton.trailingAnchor, constant: 4),
        ])

        reviewLabel.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            reviewLabel.topAnchor.constraint(equalTo: tumbNailImage.bottomAnchor, constant: 10),
            reviewLabel.leadingAnchor.constraint(equalTo: scoreLabel.trailingAnchor, constant: 4),
            reviewLabel.bottomAnchor.constraint(equalTo: scoreLabel.bottomAnchor)
        ])

        lodgingLabel.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            lodgingLabel.topAnchor.constraint(equalTo: starButton.bottomAnchor, constant: 9),
            lodgingLabel.leadingAnchor.constraint(equalTo: leadingAnchor),
            lodgingLabel.heightAnchor.constraint(equalTo: reviewLabel.heightAnchor)
            
        ])

        priceLabel.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            priceLabel.topAnchor.constraint(equalTo: lodgingLabel.bottomAnchor, constant: 8),
            priceLabel.leadingAnchor.constraint(equalTo: starButton.leadingAnchor),
            priceLabel.heightAnchor.constraint(equalTo: reviewLabel.heightAnchor),
            priceLabel.bottomAnchor.constraint(equalTo: contentView.bottomAnchor)
        ])
    }

}
