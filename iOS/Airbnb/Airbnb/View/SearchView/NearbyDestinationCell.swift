//
//  NearbyDestinationCell.swift
//  Airbnb
//
//  Created by Jason on 2022/05/27.
//

import UIKit

final class NearbyDestinationCell: UICollectionViewCell {
    
    static let identifier = "NearbyDestinationCell"
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        appendSubView()
        nearbyDestinationLayout()
    }
    
    @available(*, unavailable)
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        fatalError()
    }
    
    private let nearbyImageView: UIImageView = {
        let imageView = UIImageView()
        return imageView
    }()
    
    private let titleLabel: UILabel = {
        let title = UILabel()
        title.textColor = .systemGray2
        title.font = .systemFont(ofSize: 17, weight: .init(rawValue: 600))
        return title
    }()
    
    private let distanceLabel: UILabel = {
        let distance = UILabel()
        distance.textColor = .systemGray3
        distance.font = .systemFont(ofSize: 17, weight: .init(rawValue: 400))
        return distance
    }()
    
    func configure(image: UIImage?, title: String?, distance: String?) {
        nearbyImageView.image = image
        titleLabel.text = title
        distanceLabel.text = distance
    }
    
    func appendSubView() {
        contentView.addSubview(nearbyImageView)
        contentView.addSubview(titleLabel)
        contentView.addSubview(distanceLabel)
    }
    
    func nearbyDestinationLayout() {
        
        nearbyImageView.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            nearbyImageView.topAnchor.constraint(equalTo: topAnchor),
            nearbyImageView.bottomAnchor.constraint(equalTo: bottomAnchor),
            nearbyImageView.leadingAnchor.constraint(equalTo: contentView.leadingAnchor),
            nearbyImageView.trailingAnchor.constraint(equalTo: contentView.leadingAnchor, constant: 74)
        ])
        
        titleLabel.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            titleLabel.topAnchor.constraint(equalTo: nearbyImageView.topAnchor),
            titleLabel.leadingAnchor.constraint(equalTo: nearbyImageView.trailingAnchor, constant: 16),
            titleLabel.trailingAnchor.constraint(equalTo: contentView.trailingAnchor),
            titleLabel.heightAnchor.constraint(equalTo: nearbyImageView.heightAnchor, multiplier: 1/2)
        ])
        
        distanceLabel.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            distanceLabel.topAnchor.constraint(equalTo: titleLabel.bottomAnchor),
            distanceLabel.leadingAnchor.constraint(equalTo: titleLabel.leadingAnchor),
            distanceLabel.trailingAnchor.constraint(equalTo: titleLabel.trailingAnchor),
            distanceLabel.heightAnchor.constraint(equalTo: titleLabel.heightAnchor)
        ])
    }
    
}
