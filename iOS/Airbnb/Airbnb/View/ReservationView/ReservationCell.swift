//
//  ReservationCell.swift
//  Airbnb
//
//  Created by Jason on 2022/06/03.
//

import UIKit

final class ReservationCell: UICollectionViewCell {
    
    static let identifier = "ReservationCell"
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        appendSubView()
        reservationLayout()
    }
    
    @available(*, unavailable)
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        fatalError()
    }
    
    private let reservationImage: UIImageView = {
        let reservationImage = UIImageView()
        return reservationImage
    }()
    
    private let imageCount: UIButton = {
       let imageCount = UIButton()
        imageCount.setTitle("   1/18   ", for: .normal)
        imageCount.setTitleColor(UIColor.white, for: .normal)
        imageCount.backgroundColor = .systemGray
        imageCount.titleLabel?.font = UIFont.systemFont(ofSize: 12, weight: .light)
        imageCount.layer.cornerRadius = 10
        return imageCount
    }()
    
    private let reservationDate: UILabel = {
        let reservationDate = UILabel()
        reservationDate.numberOfLines = 0
        let dateAttributedString = NSMutableAttributedString(string: "2021년 5월 17일 - 2021년 6월 4일")
        let length = dateAttributedString.length
        dateAttributedString.addAttributes([.foregroundColor: UIColor.gray,
                                            .font: UIFont.systemFont(ofSize: 15, weight: .light)],
                                           range: NSRange(location: 0, length: length))
        reservationDate.attributedText = dateAttributedString
        return reservationDate
    }()
    
    private let reservationLocation: UILabel = {
        let location = UILabel()
        location.numberOfLines = 0
        let locationAttributedString = NSMutableAttributedString(string: "서초구, 서울, 한국")
        let length = locationAttributedString.length
        locationAttributedString.addAttributes([.foregroundColor: UIColor.black,
                                            .font: UIFont.systemFont(ofSize: 20, weight: .medium)],
                                           range: NSRange(location: 0, length: length))
        location.attributedText = locationAttributedString
        return location
    }()
    
    private let imageDescription: UILabel = {
        let imageDescription = UILabel()
        imageDescription.numberOfLines = 0
        let descriptionAttributedString = NSMutableAttributedString(string: "Spacious and Comfortable cozy house #4")
        let length = descriptionAttributedString.length
        descriptionAttributedString.addAttributes([.foregroundColor: UIColor.black,
                                            .font: UIFont.systemFont(ofSize: 17, weight: .light)],
                                           range: NSRange(location: 0, length: length))
        imageDescription.attributedText = descriptionAttributedString
        return imageDescription
    }()
    
    func configure(image: UIImage?, date: String?, location: String?, description: String?) {
        reservationImage.image = image
        reservationDate.text = date
        reservationLocation.text = location
        imageDescription.text = description
    }
    
    private func appendSubView() {
        contentView.addSubview(reservationImage)
        contentView.addSubview(imageCount)
        contentView.addSubview(reservationDate)
        contentView.addSubview(reservationLocation)
        contentView.addSubview(imageDescription)
    }
    
    private func reservationLayout() {
        reservationImage.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            reservationImage.topAnchor.constraint(equalTo: contentView.topAnchor),
            reservationImage.leadingAnchor.constraint(equalTo: contentView.leadingAnchor),
            reservationImage.trailingAnchor.constraint(equalTo: contentView.trailingAnchor),
            reservationImage.heightAnchor.constraint(equalToConstant: 200)
        ])
        
        imageCount.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            imageCount.bottomAnchor.constraint(equalTo: reservationImage.bottomAnchor, constant: -8),
            imageCount.trailingAnchor.constraint(equalTo: reservationImage.trailingAnchor, constant: -8)
        ])
        
        reservationDate.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            reservationDate.topAnchor.constraint(equalTo: reservationImage.bottomAnchor, constant: 16),
            reservationDate.leadingAnchor.constraint(equalTo: contentView.leadingAnchor)
        ])
        
        reservationLocation.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            reservationLocation.topAnchor.constraint(equalTo: reservationDate.bottomAnchor, constant: 8),
            reservationLocation.leadingAnchor.constraint(equalTo: contentView.leadingAnchor)
        ])
        
        imageDescription.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            imageDescription.topAnchor.constraint(equalTo: reservationLocation.bottomAnchor, constant: 8),
            imageDescription.leadingAnchor.constraint(equalTo: contentView.leadingAnchor),
            imageDescription.bottomAnchor.constraint(equalTo: contentView.bottomAnchor, constant: -24)
        ])
    }
}
