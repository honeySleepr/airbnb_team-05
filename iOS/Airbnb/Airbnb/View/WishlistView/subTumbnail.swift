//
//  subTumbnail.swift
//  Airbnb
//
//  Created by Jason on 2022/06/03.
//

import UIKit

final class subTumbnailCell: UICollectionViewCell {
    
    static let identifier = "subTumbnailCell"
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        appendSubView()
        subtumbnailLayout()
        subinformationLayout()
    }
    
    @available(*, unavailable)
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        fatalError()
        
    }
    
    private let subTumbNailImage: UIImageView = {
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
    
    private let subScoreLabel: UILabel = {
        let score = UILabel()
        score.numberOfLines = 0
        let scoreAttributedString = NSMutableAttributedString(string: "(후기 157개)")
        let length = scoreAttributedString.length
        scoreAttributedString.addAttributes([.foregroundColor:UIColor.black,
                                             .font: UIFont.systemFont(ofSize: 12, weight: .light)],
                                            range: NSRange(location: 0, length: length))
        score.attributedText = scoreAttributedString
        return score
    }()
    
    private let subReviewLabel: UILabel = {
        let review = UILabel()
        review.numberOfLines = 0
        let reviewAttributedString = NSMutableAttributedString(string: "4.96")
        let length = reviewAttributedString.length
        reviewAttributedString.addAttributes([.foregroundColor: UIColor.black,
                                        .font: UIFont.systemFont(ofSize: 12, weight: .light)],
                                       range: NSRange(location: 0, length: length))
        review.attributedText = reviewAttributedString
        return review
    }()
    
    private let subLodgingLabel: UILabel = {
        let lodging = UILabel()
        lodging.numberOfLines = 0
        let lodgingAttributedString = NSMutableAttributedString(string: "HOTEL강릉세인트존스호텔 풀오션뷰 ...")
        let length = lodgingAttributedString.length
        lodgingAttributedString.addAttributes([.foregroundColor: UIColor.black,
                                               .font: UIFont.systemFont(ofSize: 20, weight: .bold)],
                                              range: NSRange(location: 0, length: length))
        lodging.attributedText = lodgingAttributedString
        return lodging
    }()
    
    private let subPriceLabel: UILabel = {
       let price = UILabel()
        price.numberOfLines = 0
        let priceAttributedString = NSMutableAttributedString(string: "₩131,429 / 박")
        let length = priceAttributedString.length
        priceAttributedString.addAttributes([.foregroundColor: UIColor.black,
                                             .font: UIFont.systemFont(ofSize: 17, weight: .bold)],
                                            range: NSRange(location: 0, length: length))
        price.attributedText = priceAttributedString
        return price
    }()
    
    
    func configure(image: UIImage?, score: String?, review: String?, lodging: String?, price: String?) {
        subTumbNailImage.image = image
        subScoreLabel.text = score
        subReviewLabel.text = review
        subLodgingLabel.text = lodging
        subPriceLabel.text = price
    }
    
    private func appendSubView() {
        contentView.addSubview(subTumbNailImage)
        contentView.addSubview(superHost)
        contentView.addSubview(heart)
        contentView.addSubview(starButton)
        contentView.addSubview(subScoreLabel)
        contentView.addSubview(subReviewLabel)
        contentView.addSubview(subLodgingLabel)
        contentView.addSubview(subPriceLabel)
    }
    
    private func subtumbnailLayout() {
        subTumbNailImage.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            subTumbNailImage.topAnchor.constraint(equalTo: contentView.topAnchor),
            subTumbNailImage.leadingAnchor.constraint(equalTo: contentView.leadingAnchor),
            subTumbNailImage.trailingAnchor.constraint(equalTo: contentView.trailingAnchor),
            subTumbNailImage.heightAnchor.constraint(equalToConstant: 200)
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
    
    private func subinformationLayout() {
        starButton.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            starButton.topAnchor.constraint(equalTo: subTumbNailImage.bottomAnchor, constant: 10),
            starButton.leadingAnchor.constraint(equalTo: subTumbNailImage.leadingAnchor),
            starButton.bottomAnchor.constraint(equalTo: subScoreLabel.bottomAnchor)
        ])
        
        subScoreLabel.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            subScoreLabel.topAnchor.constraint(equalTo: subTumbNailImage.bottomAnchor, constant: 10),
            subScoreLabel.leadingAnchor.constraint(equalTo: starButton.trailingAnchor, constant: 4),
        ])

        subReviewLabel.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            subReviewLabel.topAnchor.constraint(equalTo: subTumbNailImage.bottomAnchor, constant: 10),
            subReviewLabel.leadingAnchor.constraint(equalTo: subScoreLabel.trailingAnchor, constant: 4),
            subReviewLabel.bottomAnchor.constraint(equalTo: subScoreLabel.bottomAnchor)
        ])

        subLodgingLabel.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            subLodgingLabel.topAnchor.constraint(equalTo: starButton.bottomAnchor, constant: 9),
            subLodgingLabel.leadingAnchor.constraint(equalTo: leadingAnchor),
            subLodgingLabel.heightAnchor.constraint(equalTo: subReviewLabel.heightAnchor)
            
        ])

        subPriceLabel.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            subPriceLabel.topAnchor.constraint(equalTo: subLodgingLabel.bottomAnchor, constant: 8),
            subPriceLabel.leadingAnchor.constraint(equalTo: starButton.leadingAnchor),
            subPriceLabel.heightAnchor.constraint(equalTo: subReviewLabel.heightAnchor),
            subPriceLabel.bottomAnchor.constraint(equalTo: contentView.bottomAnchor)
        ])
    }

}

