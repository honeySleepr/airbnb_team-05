//
//  TumbnailCell.swift
//  Airbnb
//
//  Created by Jason on 2022/05/31.
//

import UIKit

final class Tumbnail:UICollectionViewCell {
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        appendSubView()
        tumbnailLayout()
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
    
    private let superHost: UILabel = {
        let superHost = UILabel()
        let hostFont = UIFont(name: "Helvetica", size: 24)
        let superHostAttributedString = NSMutableAttributedString(string: "슈퍼호스트")
        superHost.attributedText = superHostAttributedString
        return superHost
    }()
    
    private let heart: UIButton = {
        let heart = UIButton()
        heart.setImage(systemName: "heart.fill")
        return heart
    }()
    
    func configure(image: UIImage?) {
        tumbNailImage.image = image
    }
    
    private func appendSubView() {
        contentView.addSubview(tumbNailImage)
        contentView.addSubview(superHost)
        contentView.addSubview(heart)
    }
    
    private func tumbnailLayout() {
        tumbNailImage.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            tumbNailImage.topAnchor.constraint(equalTo: contentView.topAnchor),
            tumbNailImage.leadingAnchor.constraint(equalTo: contentView.leadingAnchor),
            tumbNailImage.trailingAnchor.constraint(equalTo: contentView.trailingAnchor),
            tumbNailImage.bottomAnchor.constraint(equalTo: contentView.bottomAnchor)
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

}
