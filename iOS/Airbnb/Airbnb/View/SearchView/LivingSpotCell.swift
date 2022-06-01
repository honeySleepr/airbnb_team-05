//
//  LivingSpotCell.swift
//  Airbnb
//
//  Created by Jason on 2022/05/27.
//

import UIKit

final class LivingSpotCell: UICollectionViewCell {
    
    static let identifier = "LivingSpotCell"
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        appendSubView()
        LivingSpotLayout()
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        fatalError()
    }
    
    private let LivingSpotImageView: UIImageView = {
        let livingSpot = UIImageView()
        livingSpot.clipsToBounds = true
        livingSpot.layer.cornerRadius = 8
        return livingSpot
    }()
    
    private let titleLabel: UILabel = {
        let title = UILabel()
        title.numberOfLines = 0
        title.textColor = .systemGray
        title.font = .systemFont(ofSize: 17, weight: .init(rawValue: 600))
        return title
    }()
    
    func configure(image: UIImage?, title: String?) {
        LivingSpotImageView.image = image
        titleLabel.text = title
    }
    
    func appendSubView() {
        contentView.addSubview(LivingSpotImageView)
        contentView.addSubview(titleLabel)
    }
    
    func LivingSpotLayout() {
        LivingSpotImageView.translatesAutoresizingMaskIntoConstraints = false
        titleLabel.translatesAutoresizingMaskIntoConstraints = false
        
        NSLayoutConstraint.activate([
            titleLabel.leadingAnchor.constraint(equalTo: contentView.leadingAnchor),
            titleLabel.trailingAnchor.constraint(equalTo: contentView.trailingAnchor),
            titleLabel.bottomAnchor.constraint(equalTo: contentView.bottomAnchor),
            titleLabel.heightAnchor.constraint(equalToConstant: 44)
        ])
        
        NSLayoutConstraint.activate([
            LivingSpotImageView.leadingAnchor.constraint(equalTo: contentView.leadingAnchor),
            LivingSpotImageView.trailingAnchor.constraint(equalTo: contentView.trailingAnchor),
            LivingSpotImageView.topAnchor.constraint(equalTo: contentView.topAnchor),
            LivingSpotImageView.bottomAnchor.constraint(equalTo: titleLabel.topAnchor, constant: -16)
        ])
    }
    
}

